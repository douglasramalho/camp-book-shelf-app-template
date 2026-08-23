package br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Tokens de espaçamento centralizados. Use-os em vez de valores de padding/gap
 * soltos nos componentes.
 */
data class BookShelfSpacing(
    val spacing2: Dp = 2.dp,
    val spacing4: Dp = 4.dp,
    val spacing8: Dp = 8.dp,
    val spacing12: Dp = 12.dp,
    val spacing16: Dp = 16.dp,
    val spacing20: Dp = 20.dp,
    val spacing24: Dp = 24.dp,
    val spacing32: Dp = 32.dp,
    val spacing40: Dp = 40.dp,
    val spacing48: Dp = 48.dp,
)

val LocalBookShelfSpacing = staticCompositionLocalOf { BookShelfSpacing() }
