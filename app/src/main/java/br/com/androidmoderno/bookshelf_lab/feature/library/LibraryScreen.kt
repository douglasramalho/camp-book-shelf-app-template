package br.com.androidmoderno.bookshelf_lab.feature.library

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import br.com.androidmoderno.bookshelf_lab.R
import br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.component.BookShelfTopAppBar
import br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.component.EmptyContent
import br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.theme.BookShelfTheme

/**
 * Tela da biblioteca pessoal. Salvar livros ainda não foi implementado, então só o
 * estado vazio existe. [onDiscoverClick] leva o usuário para a aba de descoberta.
 */
@Composable
fun LibraryScreen(
    modifier: Modifier = Modifier,
    onDiscoverClick: () -> Unit = {},
) {
    Scaffold(
        topBar = { BookShelfTopAppBar(title = stringResource(R.string.top_level_destination_library)) },
        modifier = modifier,
    ) { innerPadding ->
        EmptyContent(
            title = stringResource(R.string.library_empty_title),
            message = stringResource(R.string.library_empty_message),
            actionLabel = stringResource(R.string.library_empty_action),
            onActionClick = onDiscoverClick,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@PreviewLightDark
@Composable
private fun LibraryScreenPreview() {
    BookShelfTheme {
        LibraryScreen()
    }
}
