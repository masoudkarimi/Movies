package ir.masoudkarimi.movies.tv.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

internal val TvBackground = Color(0xFF2F2F2F)
internal val TvOnBackground = Color(0xFFF7F2FA)
internal val TvSurface = Color(0xFF1F1F1F)
internal val TvSurfaceFocused = Color(0xFF2A2A2A)
internal val TvDrawerItemFocused = Color(0xFF3A3A3A)
internal val TvDrawerItemSelected = Color(0xFFE8E3F0)
internal val TvDrawerItemSelectedContent = Color(0xFF211B2D)
internal val TvContentInactive = Color(0x8CFFFFFF)
internal val TvButtonContainer = Color(0xFFE8E3F0)
internal val TvButtonContent = Color(0xFF211B2D)
internal val TvButtonFocusedContainer = Color(0xFFFFFFFF)
internal val TvButtonFocusedContent = Color(0xFF111111)
internal val TvButtonDisabledContainer = Color(0x66E8E3F0)
internal val TvButtonDisabledContent = Color(0x66211B2D)

@Immutable
data class TvMoviesColors(
    val cardContainer: Color,
    val cardFocusedContainer: Color,
    val cardContent: Color,
    val drawerItemContainer: Color,
    val drawerItemContent: Color,
    val drawerItemInactiveContent: Color,
    val drawerItemFocusedContainer: Color,
    val drawerItemFocusedContent: Color,
    val drawerItemSelectedContainer: Color,
    val drawerItemSelectedContent: Color,
    val buttonContainer: Color,
    val buttonContent: Color,
    val buttonFocusedContainer: Color,
    val buttonFocusedContent: Color,
    val buttonDisabledContainer: Color,
    val buttonDisabledContent: Color,
)

internal val LocalTvMoviesColors = staticCompositionLocalOf {
    TvMoviesColors(
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
}
