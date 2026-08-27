package br.com.androidmoderno.bookshelf.core.ui.designsystem.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Dimensões próprias do design system, fora da escala genérica de [BookShelfSpacing].
 * As capas seguem proporção 2:3 (largura:altura), típica de capas de livro.
 */
data class BookShelfDimensions(
    val bookCoverSmallWidth: Dp = 56.dp,
    val bookCoverSmallHeight: Dp = 84.dp,
    val bookCoverLargeWidth: Dp = 140.dp,
    val bookCoverLargeHeight: Dp = 210.dp,
    val minTouchTarget: Dp = 48.dp,
    val searchFieldHeight: Dp = 56.dp,
    val borderWidth: Dp = 1.dp,
    val iconSizeSmall: Dp = 18.dp,
    val iconSize: Dp = 24.dp,
    val iconSizeLarge: Dp = 64.dp,
)

val LocalBookShelfDimensions = staticCompositionLocalOf { BookShelfDimensions() }
