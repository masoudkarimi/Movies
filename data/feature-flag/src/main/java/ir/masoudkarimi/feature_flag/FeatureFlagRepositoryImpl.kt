package ir.masoudkarimi.feature_flag

import javax.inject.Inject

class FeatureFlagRepositoryImpl @Inject constructor(

): FeatureFlagRepository {
    override suspend fun isEnabled(flag: FeatureFlag): Boolean {
        return false
    }
}