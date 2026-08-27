package br.com.androidmoderno.bookshelf.feature.bookdetails

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import br.com.androidmoderno.bookshelf.R
import br.com.androidmoderno.bookshelf.core.ui.designsystem.component.BookShelfTopAppBar
import br.com.androidmoderno.bookshelf.core.ui.designsystem.component.EmptyContent
import br.com.androidmoderno.bookshelf.core.ui.designsystem.theme.BookShelfTheme

/**
 * Tela de detalhes de um livro. [bookId] identifica a obra a carregar — a busca real
 * dos dados ainda não existe, então a tela sempre mostra o estado vazio por enquanto.
 */
@Composable
fun BookDetailsScreen(
    bookId: String,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            BookShelfTopAppBar(
                title = stringResource(R.string.book_details_screen_title),
                showBackButton = true,
                onBackClick = onBackClick,
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        EmptyContent(
            title = stringResource(R.string.book_details_empty_title),
            message = stringResource(R.string.book_details_empty_message),
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@PreviewLightDark
@Composable
private fun BookDetailsScreenPreview() {
    BookShelfTheme {
        BookDetailsScreen(bookId = "OL1234567W")
    }
}
