package ir.masoudkarimi.feature_flag.providers

import ir.masoudkarimi.feature_flag.FeatureState
import javax.inject.Inject

class FirebaseFlagProvider @Inject constructor(): FeatureFlagProvider {
    override val identifier: String = "firebase"

    // For the sake of simplicity we preserve the flags in a hashmap
    private val flags = mutableMapOf(
        "addtobasket" to true
    )

    override suspend fun getValue(key: String): FeatureState {
        return flags.getOrDefault(key, false).let(::FeatureState)
    }

    override suspend fun setValue(key: String, isEnable: Boolean) {
        flags[key] = isEnable
    }
}