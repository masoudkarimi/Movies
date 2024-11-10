package ir.masoudkarimi.feature_flag

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.SpyK
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import ir.masoudkarimi.feature_flag.config.DefaultProviderSorter
import ir.masoudkarimi.feature_flag.config.FeatureFlagPriorityConfig
import ir.masoudkarimi.feature_flag.config.ProviderSorter
import ir.masoudkarimi.feature_flag.providers.FirebaseFlagProvider
import ir.masoudkarimi.feature_flag.providers.GrowthBookFlagProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class FeatureFlagRepositoryImplTest {

    companion object {
        private const val ADD_TO_BASKET = "addtobasket"
    }

    @get:Rule
    val mockRule = MockKRule(this)

    private val featureFlagConfig: FeatureFlagPriorityConfig = mockk()

    @SpyK
    var providerSorter: ProviderSorter = DefaultProviderSorter(featureFlagConfig)

    @SpyK
    var firebaseFlagProvider = FirebaseFlagProvider()

    @SpyK
    var growthBookFlagProvider = GrowthBookFlagProvider()

    private lateinit var featureFlagRepository: FeatureFlagRepositoryImpl

    private fun setupRepository() {
        featureFlagRepository = FeatureFlagRepositoryImpl(
            setOf(firebaseFlagProvider, growthBookFlagProvider),
            providerSorter
        )
    }

    @Test
    fun `isEnabled returns true when all providers return true`() = runTest {
        every { featureFlagConfig.currentPriorities } returns listOf("growthbook", "firebase")
        coEvery { growthBookFlagProvider.getValue(ADD_TO_BASKET) } returns FeatureState(isEnable = true)
        coEvery { firebaseFlagProvider.getValue(ADD_TO_BASKET) } returns FeatureState(isEnable = true)

        setupRepository()

        val isEnabled = featureFlagRepository.isEnabled(ADD_TO_BASKET).first()
        assertEquals(true, isEnabled)
    }

    @Test
    fun `isEnabled returns false when any provider returns false`() = runTest {
        every { featureFlagConfig.currentPriorities } returns listOf("growthbook", "firebase")
        coEvery { growthBookFlagProvider.getValue(ADD_TO_BASKET) } returns FeatureState(isEnable = true)
        coEvery { firebaseFlagProvider.getValue(ADD_TO_BASKET) } returns FeatureState(isEnable = false)

        setupRepository()

        val isEnabled = featureFlagRepository.isEnabled(ADD_TO_BASKET).first()
        assertEquals(false, isEnabled)
    }

    @Test
    fun `isEnabled does not call next providers when top priority provider (firebase) returns false`() = runTest {
        every { featureFlagConfig.currentPriorities } returns listOf("firebase", "growthbook")
        coEvery { firebaseFlagProvider.getValue(ADD_TO_BASKET) } returns FeatureState(isEnable = false)
        coEvery { growthBookFlagProvider.getValue(ADD_TO_BASKET) } returns FeatureState(isEnable = true)

        setupRepository()

        val isEnabled = featureFlagRepository.isEnabled(ADD_TO_BASKET).first()
        assertEquals(false, isEnabled)

        coVerify { firebaseFlagProvider.getValue(ADD_TO_BASKET) }
        coVerify(exactly = 0) { growthBookFlagProvider.getValue(ADD_TO_BASKET) }
    }

    @Test
    fun `isEnabled does not call next providers when top priority provider (growthBook) returns false`() = runTest {
        every { featureFlagConfig.currentPriorities } returns listOf("growthbook", "firebase")
        coEvery { growthBookFlagProvider.getValue(ADD_TO_BASKET) } returns FeatureState(isEnable = false)
        coEvery { firebaseFlagProvider.getValue(ADD_TO_BASKET) } returns FeatureState(isEnable = true)

        setupRepository()

        val isEnabled = featureFlagRepository.isEnabled(ADD_TO_BASKET).first()
        assertEquals(false, isEnabled)

        coVerify { growthBookFlagProvider.getValue(ADD_TO_BASKET) }
        coVerify(exactly = 0) { firebaseFlagProvider.getValue(ADD_TO_BASKET) }
    }

    @Test
    fun `isEnabled returns default false when no providers are available`() = runTest {
        every { providerSorter.sortProviders(any()) } returns emptyList()

        setupRepository()

        val isEnabled = featureFlagRepository.isEnabled(ADD_TO_BASKET).first()
        assertEquals(false, isEnabled)
    }
}