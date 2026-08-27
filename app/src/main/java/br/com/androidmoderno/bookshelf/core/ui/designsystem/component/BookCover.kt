package br.com.androidmoderno.bookshelf.core.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.androidmoderno.bookshelf.R
import br.com.androidmoderno.bookshelf.core.ui.designsystem.theme.BookShelfTheme
import coil3.compose.SubcomposeAsyncImage

/** Tamanhos suportados para [BookCover]; as dimensões reais vêm de [BookShelfTheme.dimensions]. */
enum class BookCoverSize {
    Small,
    Large,
}

/**
 * Exibe a capa de um livro, sempre com a mesma proporção vertical (2:3) — o box tem
 * tamanho fixo em todos os estados (placeholder, carregando, sucesso, erro) para não
 * causar layout shift na lista.
 */
@Composable
fun BookCover(
    imageUrl: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    size: BookCoverSize = BookCoverSize.Small,
) {
    val dimensions = BookShelfTheme.dimensions
    val (width, height) = when (size) {
        BookCoverSize.Small -> dimensions.bookCoverSmallWidth to dimensions.bookCoverSmallHeight
        BookCoverSize.Large -> dimensions.bookCoverLargeWidth to dimensions.bookCoverLargeHeight
    }
    val shape = when (size) {
        BookCoverSize.Small -> MaterialTheme.shapes.small
        BookCoverSize.Large -> MaterialTheme.shapes.medium
    }
    val coverModifier = modifier.size(width, height)

    if (imageUrl.isNullOrBlank()) {
        BookCoverPlaceholder(
            modifier = coverModifier,
            shape = shape,
            contentDescription = contentDescription,
        )
        return
    }

    SubcomposeAsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
        modifier = coverModifier.clip(shape),
        contentScale = ContentScale.Crop,
        loading = { BookCoverPlaceholder(modifier = Modifier.fillMaxSize(), shape = shape) },
        error = { BookCoverPlaceholder(modifier = Modifier.fillMaxSize(), shape = shape) },
    )
}

@Composable
private fun BookCoverPlaceholder(
    modifier: Modifier = Modifier,
    shape: Shape,
    contentDescription: String? = stringResource(R.string.cd_book_cover_placeholder),
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .let {
                if (contentDescription != null) {
                    it.semantics { this.contentDescription = contentDescription }
                } else {
                    it
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Outlined.MenuBook,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(BookShelfTheme.dimensions.iconSize),
        )
    }
}

@PreviewLightDark
@Composable
private fun BookCoverPreview() {
    BookShelfTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Row(
                modifier = Modifier.size(200.dp, 220.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                BookCover(
                    imageUrl = null,
                    contentDescription = "Capa de O Guia do Mochileiro das Galáxias",
                    size = BookCoverSize.Small,
                )
                BookCover(
                    imageUrl = null,
                    contentDescription = "Capa de O Guia do Mochileiro das Galáxias",
                    size = BookCoverSize.Large,
                )
            }
        }
    }
}
