package ir.masoudkarimi.core.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

fun <T> ViewModel.executeUseCase(
    useCase: suspend () -> Result<T>,
    onSuccess: (T) -> Unit = {},
    onFailure: (Throwable) -> Unit = {}
) {
    viewModelScope.launch {
        useCase().fold(onSuccess, onFailure)
    }
}