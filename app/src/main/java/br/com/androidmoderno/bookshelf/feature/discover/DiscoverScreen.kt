package br.com.androidmoderno.bookshelf.feature.discover

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import br.com.androidmoderno.bookshelf.R
import br.com.androidmoderno.bookshelf.core.ui.designsystem.component.BookSearchField
import br.com.androidmoderno.bookshelf.core.ui.designsystem.component.BookShelfTopAppBar
import br.com.androidmoderno.bookshelf.core.ui.designsystem.component.EmptyContent
import br.com.androidmoderno.bookshelf.core.ui.designsystem.theme.BookShelfTheme

/**
 * Tela de descoberta de livros. Por ora só o campo de pesquisa e o estado vazio
 * inicial existem — busca real, loading e erro chegam quando a camada de dados for
 * implementada.
 */
@Composable
fun DiscoverScreen(modifier: Modifier = Modifier) {
    var query by remember { mutableStateOf("") }

    Scaffold(
        topBar = { BookShelfTopAppBar(title = stringResource(R.string.top_level_destination_discover)) },
        modifier = modifier,
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            BookSearchField(
                query = query,
                onQueryChange = { query = it },
                onSearch = {},
                onClear = { query = "" },
                modifier = Modifier.padding(BookShelfTheme.spacing.spacing16),
            )
            EmptyContent(
                title = stringResource(R.string.discover_empty_title),
                message = stringResource(R.string.discover_empty_message),
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun DiscoverScreenPreview() {
    BookShelfTheme {
        DiscoverScreen()
    }
}
