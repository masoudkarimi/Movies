package ir.masoudkarimi.feature_flag.config

import ir.masoudkarimi.feature_flag.providers.FeatureFlagProvider
import javax.inject.Inject

class DefaultProviderSorter @Inject constructor(
    private val featureFlagPriorityConfig: FeatureFlagPriorityConfig
) : ProviderSorter {

    override fun sortProviders(providers: Set<FeatureFlagProvider>): List<FeatureFlagProvider> {
        return providers.sortedBy { provider ->
            featureFlagPriorityConfig.currentPriorities.indexOf(provider.identifier)
        }
    }
}