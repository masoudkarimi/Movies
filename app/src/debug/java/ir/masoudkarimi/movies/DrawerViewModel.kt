package ir.masoudkarimi.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.masoudkarimi.feature_flag.FeatureFlag
import ir.masoudkarimi.feature_flag.FeatureFlagRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrawerViewModel @Inject constructor(
    private val featureFlagRepository: FeatureFlagRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadFeatureFlags()
    }

    private fun loadFeatureFlags() {
        viewModelScope.launch {
            featureFlagRepository.isEnabled(FeatureFlag.ADD_TO_BASKET_IN_LIST_SCREEN.key)
                .collect { isAddToBasketEnabled ->
                    _uiState.update { it.copy(isBasketEnabled = isAddToBasketEnabled) }
                }
        }
    }

    fun setAddToBasketFlag(checked: Boolean) {
        viewModelScope.launch {
            featureFlagRepository.setEnabled(FeatureFlag.ADD_TO_BASKET_IN_LIST_SCREEN.key, checked)
        }
    }
}