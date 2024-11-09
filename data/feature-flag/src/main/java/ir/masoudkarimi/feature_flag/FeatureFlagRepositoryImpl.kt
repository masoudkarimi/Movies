package ir.masoudkarimi.feature_flag

import ir.masoudkarimi.feature_flag.providers.FeatureFlagProvider
import ir.masoudkarimi.feature_flag.config.ProviderSorter
import javax.inject.Inject

class FeatureFlagRepositoryImpl @Inject constructor(
    private val providers: List<@JvmSuppressWildcards FeatureFlagProvider>,
    private val providerSorter: ProviderSorter
) : FeatureFlagRepository {
    private val sortedProviders by lazy {
        providerSorter.sortProviders(providers)
    }

    override suspend fun isEnabled(flag: String): Boolean {
        // If there isn't any provider, the feature is disabled
        if (sortedProviders.isEmpty()) return false
        return sortedProviders.all { provider ->
            provider.getValue(flag).isEnable
        }
    }
}