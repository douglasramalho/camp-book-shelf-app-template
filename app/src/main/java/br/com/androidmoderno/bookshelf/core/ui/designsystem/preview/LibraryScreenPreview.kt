package br.com.androidmoderno.bookshelf.core.ui.designsystem.preview

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import br.com.androidmoderno.bookshelf.core.ui.designsystem.component.BookShelfNavigationBar
import br.com.androidmoderno.bookshelf.core.ui.designsystem.component.BookShelfNavigationItem
import br.com.androidmoderno.bookshelf.core.ui.designsystem.component.BookShelfTopAppBar
import br.com.androidmoderno.bookshelf.core.ui.designsystem.component.EmptyContent
import br.com.androidmoderno.bookshelf.core.ui.designsystem.theme.BookShelfTheme

private val LibraryPreviewNavItems = listOf(
    BookShelfNavigationItem(
        id = "discover",
        label = "Descobrir",
        icon = Icons.Outlined.Explore,
        selectedIcon = Icons.Filled.Explore,
    ),
    BookShelfNavigationItem(
        id = "library",
        label = "Biblioteca",
        icon = Icons.Outlined.AutoStories,
        selectedIcon = Icons.Filled.AutoStories,
    ),
)

/**
 * Demonstração do estado vazio da biblioteca, com topo e navegação inferior visuais.
 * Dados locais de preview, sem tela real nem navegação.
 */
@PreviewLightDark
@Composable
private fun LibraryEmptyPreview() {
    BookShelfTheme {
        Scaffold(
            topBar = { BookShelfTopAppBar(title = "Biblioteca") },
            bottomBar = {
                BookShelfNavigationBar(
                    items = LibraryPreviewNavItems,
                    selectedItemId = "library",
                    onItemSelected = {},
                )
            },
        ) { innerPadding ->
            EmptyContent(
                title = "Sua biblioteca está vazia",
                message = "Livros que você salvar aparecem aqui.",
                actionLabel = "Descobrir livros",
                onActionClick = {},
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}
