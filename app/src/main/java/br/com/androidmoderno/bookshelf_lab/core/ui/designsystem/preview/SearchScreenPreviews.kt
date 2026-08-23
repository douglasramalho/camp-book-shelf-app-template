package br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.preview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.component.BookListItem
import br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.component.BookSearchField
import br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.component.BookShelfTopAppBar
import br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.component.EmptyContent
import br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.component.ErrorContent
import br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.component.LoadingContent
import br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.theme.BookShelfTheme

/**
 * Demonstrações da tela de pesquisa em seus diferentes estados. Só compõem
 * componentes já existentes do design system com dados locais — sem tela real,
 * ViewModel ou navegação.
 */
@PreviewLightDark
@Composable
private fun SearchScreenPreview() {
    BookShelfTheme {
        Scaffold(topBar = { BookShelfTopAppBar(title = "Descobrir") }) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                BookSearchField(
                    query = "Isaac Asimov",
                    onQueryChange = {},
                    onSearch = {},
                    onClear = {},
                    modifier = Modifier.padding(BookShelfTheme.spacing.spacing16),
                )
                LazyColumn {
                    items(PreviewBooks) { book ->
                        BookListItem(
                            title = book.title,
                            authors = book.authors,
                            coverUrl = book.coverUrl,
                            firstPublishedYear = book.firstPublishedYear,
                            isFavorite = book.isFavorite,
                            onClick = {},
                            onFavoriteClick = {},
                        )
                    }
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun SearchLoadingPreview() {
    BookShelfTheme {
        Scaffold(topBar = { BookShelfTopAppBar(title = "Descobrir") }) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                BookSearchField(
                    query = "Asimov",
                    onQueryChange = {},
                    onSearch = {},
                    onClear = {},
                    modifier = Modifier.padding(BookShelfTheme.spacing.spacing16),
                )
                LoadingContent(message = "Buscando livros…")
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun SearchEmptyPreview() {
    BookShelfTheme {
        Scaffold(topBar = { BookShelfTopAppBar(title = "Descobrir") }) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                BookSearchField(
                    query = "xkjqwzy",
                    onQueryChange = {},
                    onSearch = {},
                    onClear = {},
                    modifier = Modifier.padding(BookShelfTheme.spacing.spacing16),
                )
                EmptyContent(
                    title = "Nenhum resultado",
                    message = "Não encontramos livros para essa pesquisa. Tente outro título, autor ou assunto.",
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun SearchErrorPreview() {
    BookShelfTheme {
        Scaffold(topBar = { BookShelfTopAppBar(title = "Descobrir") }) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                BookSearchField(
                    query = "Asimov",
                    onQueryChange = {},
                    onSearch = {},
                    onClear = {},
                    modifier = Modifier.padding(BookShelfTheme.spacing.spacing16),
                )
                ErrorContent(
                    title = "Não foi possível carregar",
                    message = "Verifique sua conexão e tente novamente.",
                    onRetryClick = {},
                )
            }
        }
    }
}
