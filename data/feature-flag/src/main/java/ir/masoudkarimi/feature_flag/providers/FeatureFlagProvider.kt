package ir.masoudkarimi.feature_flag.providers

import ir.masoudkarimi.feature_flag.FeatureState

interface FeatureFlagProvider {
    val identifier: String
    suspend fun getValue(key: String): FeatureState
    suspend fun setValue(key: String, isEnable: Boolean)
}