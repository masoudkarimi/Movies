package ir.masoudkarimi.feature_flag.config

import javax.inject.Inject

class FeatureFlagPriorityConfig @Inject constructor() {
    // This can be inquired from an API
    val currentPriorities = listOf(
        "growthbook",
        "firebase"
    )
}