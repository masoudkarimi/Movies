package ir.masoudkarimi.feature_flag

import kotlinx.coroutines.flow.Flow

interface FeatureFlagRepository {
    fun isEnabled(flag: String): Flow<Boolean>
    fun setEnabled(flag: String, isEnabled: Boolean)
}