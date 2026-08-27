package br.com.androidmoderno.bookshelf.core.ui.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.androidmoderno.bookshelf.R
import br.com.androidmoderno.bookshelf.core.ui.designsystem.theme.BookShelfTheme

/**
 * Representa um livro em uma lista de resultados. Sem [androidx.compose.material3.Card]
 * nem elevação: a lista inteira já se apoia no [Surface] de fundo da tela, então cada
 * item só precisa de padding e da ripple do próprio clique.
 *
 * A capa entra com `contentDescription = null`: o título ao lado já descreve o livro,
 * então a imagem é tratada como decorativa para leitores de tela.
 *
 * Nenhum divisor é desenhado aqui — quem monta a lista (`LazyColumn`) decide se precisa
 * de um `HorizontalDivider` entre os itens.
 */
@Composable
fun BookListItem(
    title: String,
    authors: String,
    coverUrl: String?,
    firstPublishedYear: Int?,
    isFavorite: Boolean,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = BookShelfTheme.spacing

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .clickable(onClick = onClick)
            .padding(horizontal = spacing.spacing16, vertical = spacing.spacing12),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.spacing12),
    ) {
        BookCover(
            imageUrl = coverUrl,
            contentDescription = null,
            size = BookCoverSize.Small,
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(spacing.spacing2),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            if (authors.isNotBlank()) {
                Text(
                    text = authors,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            if (firstPublishedYear != null) {
                Text(
                    text = firstPublishedYear.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        val favoriteDescription = stringResource(
            if (isFavorite) R.string.cd_favorite_remove else R.string.cd_favorite_add,
        )

        IconButton(onClick = onFavoriteClick) {
            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = favoriteDescription,
                tint = if (isFavorite) {
                    MaterialTheme.colorScheme.tertiary
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                },
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun BookListItemPreview() {
    BookShelfTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Column(modifier = Modifier.width(360.dp)) {
                BookListItem(
                    title = "O Guia do Mochileiro das Galáxias",
                    authors = "Douglas Adams",
                    coverUrl = null,
                    firstPublishedYear = 1979,
                    isFavorite = true,
                    onClick = {},
                    onFavoriteClick = {},
                )
                BookListItem(
                    title = "Fundação",
                    authors = "Isaac Asimov",
                    coverUrl = null,
                    firstPublishedYear = null,
                    isFavorite = false,
                    onClick = {},
                    onFavoriteClick = {},
                )
                BookListItem(
                    title = "Compilação de Contos Populares Sem Autoria Catalogada na Open Library",
                    authors = "",
                    coverUrl = null,
                    firstPublishedYear = 1888,
                    isFavorite = false,
                    onClick = {},
                    onFavoriteClick = {},
                )
            }
        }
    }
}
