package ir.masoudkarimi.movies

import androidx.compose.runtime.Composable

@Composable
fun ContentProvider(content: @Composable () -> Unit) {
    content()
}