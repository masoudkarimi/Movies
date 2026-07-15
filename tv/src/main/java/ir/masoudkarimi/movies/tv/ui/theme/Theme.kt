package ir.masoudkarimi.movies.tv.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.darkColorScheme

private val TvColorScheme = darkColorScheme(
    background = TvBackground,
    onBackground = TvOnBackground,
    surface = TvSurface,
    onSurface = TvOnBackground,
    surfaceVariant = TvSurfaceFocused,
    onSurfaceVariant = TvOnBackground,
    secondaryContainer = TvDrawerItemSelected,
    onSecondaryContainer = TvDrawerItemSelectedContent,
    inverseSurface = TvDrawerItemFocused,
    inverseOnSurface = TvOnBackground,
    border = TvDrawerItemSelected,
)

private val TvExtraColors = TvMoviesColors(
    cardContainer = TvSurface,
    cardFocusedContainer = TvSurfaceFocused,
    cardContent = TvOnBackground,
    drawerItemContainer = Color.Transparent,
    drawerItemContent = TvOnBackground,
    drawerItemInactiveContent = TvContentInactive,
    drawerItemFocusedContainer = TvDrawerItemFocused,
    drawerItemFocusedContent = TvOnBackground,
    drawerItemSelectedContainer = TvDrawerItemSelected,
    drawerItemSelectedContent = TvDrawerItemSelectedContent,
    buttonContainer = TvButtonContainer,
    buttonContent = TvButtonContent,
    buttonFocusedContainer = TvButtonFocusedContainer,
    buttonFocusedContent = TvButtonFocusedContent,
    buttonDisabledContainer = TvButtonDisabledContainer,
    buttonDisabledContent = TvButtonDisabledContent,
)

@Composable
fun TvMoviesTheme(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalTvMoviesColors provides TvExtraColors) {
        MaterialTheme(
            colorScheme = TvColorScheme,
            content = content,
        )
    }
}

val MaterialTheme.tvMoviesColors: TvMoviesColors
    @Composable
    get() = LocalTvMoviesColors.current
