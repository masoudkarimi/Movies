package ir.masoudkarimi.feature_flag

import ir.masoudkarimi.feature_flag.providers.FeatureFlagProvider
import ir.masoudkarimi.feature_flag.config.ProviderSorter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class FeatureFlagRepositoryImpl @Inject constructor(
    private val providers: Set<@JvmSuppressWildcards FeatureFlagProvider>,
    private val providerSorter: ProviderSorter
) : FeatureFlagRepository {
    private val sortedProviders by lazy {
        providerSorter.sortProviders(providers)
    }

    private val flagsState = MutableStateFlow(mapOf<String, Boolean>())

    override fun isEnabled(flag: String): Flow<Boolean> {
        return flagsState
            .map {
                it.filterKeys { key -> key == flag }
            }
            .map {
                it[flag] ?: false
            }
            .onStart {
                if (sortedProviders.isEmpty()) {
                    flagsState.update { map ->
                        map + (flag to false)
                    }
                    return@onStart
                }

                val value = sortedProviders.all { provider ->
                    provider.getValue(flag).isEnable
                }

                flagsState.update { map ->
                    map + (flag to value)
                }
            }

    }

    override fun setEnabled(flag: String, isEnabled: Boolean) {
        flagsState.update { map ->
            map + (flag to isEnabled)
        }
    }
}