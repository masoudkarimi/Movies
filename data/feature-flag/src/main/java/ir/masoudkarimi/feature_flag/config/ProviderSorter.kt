package ir.masoudkarimi.feature_flag.config

import ir.masoudkarimi.feature_flag.providers.FeatureFlagProvider

interface ProviderSorter {
    fun sortProviders(providers: Set<FeatureFlagProvider>): List<FeatureFlagProvider>
}