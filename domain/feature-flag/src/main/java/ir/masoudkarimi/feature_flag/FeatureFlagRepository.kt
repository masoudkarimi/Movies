package ir.masoudkarimi.feature_flag

interface FeatureFlagRepository {
    suspend fun isEnabled(flag: FeatureFlag): Boolean
}