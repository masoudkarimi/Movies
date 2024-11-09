package ir.masoudkarimi.feature_flag.config

import ir.masoudkarimi.feature_flag.providers.FeatureFlagProvider

interface ProviderSorter {
    fun sortProviders(providers: List<FeatureFlagProvider>): List<FeatureFlagProvider>
}