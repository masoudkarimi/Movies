package ir.masoudkarimi.movies.tv.ui.theme

import androidx.compose.runtime.Composable
import androidx.tv.material3.ButtonColors
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.MaterialTheme

@Composable
fun tvButtonColors(): ButtonColors {
    val colors = MaterialTheme.tvMoviesColors

    return ButtonDefaults.colors(
        containerColor = colors.buttonContainer,
        contentColor = colors.buttonContent,
        focusedContainerColor = colors.buttonFocusedContainer,
        focusedContentColor = colors.buttonFocusedContent,
        pressedContainerColor = colors.buttonFocusedContainer,
        pressedContentColor = colors.buttonFocusedContent,
        disabledContainerColor = colors.buttonDisabledContainer,
        disabledContentColor = colors.buttonDisabledContent,
    )
}
