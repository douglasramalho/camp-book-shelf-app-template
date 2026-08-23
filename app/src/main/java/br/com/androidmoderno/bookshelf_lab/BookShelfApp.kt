package br.com.androidmoderno.bookshelf_lab

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.component.BookShelfNavigationBar
import br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.component.BookShelfNavigationItem
import br.com.androidmoderno.bookshelf_lab.feature.bookdetails.BookDetailsScreen
import br.com.androidmoderno.bookshelf_lab.feature.discover.DiscoverScreen
import br.com.androidmoderno.bookshelf_lab.feature.library.LibraryScreen
import br.com.androidmoderno.bookshelf_lab.navigation.BookDetails
import br.com.androidmoderno.bookshelf_lab.navigation.BottomNavKey
import br.com.androidmoderno.bookshelf_lab.navigation.rememberBookShelfNavEntries
import br.com.androidmoderno.bookshelf_lab.navigation.rememberBookShelfNavState
import br.com.androidmoderno.bookshelf_lab.navigation.rememberTopLevelDestinations

/**
 * Raiz de navegação do BookShelf. Cada aba (Descobrir/Biblioteca) tem sua própria back
 * stack: trocar de aba preserva o histórico e o estado das outras abas, em vez de
 * reiniciá-las. A bottom bar some ao entrar nos detalhes de um livro e volta ao
 * retornar à raiz da aba.
 */
@Composable
fun BookShelfApp(modifier: Modifier = Modifier) {
    val navState = rememberBookShelfNavState()
    val topLevelDestinations = rememberTopLevelDestinations()

    val entries = rememberBookShelfNavEntries(
        navState = navState,
        entryProvider = entryProvider {
            entry<BottomNavKey.Discover> { DiscoverScreen() }
            entry<BottomNavKey.Library> {
                LibraryScreen(onDiscoverClick = { navState.selectTab(BottomNavKey.Discover) })
            }
            entry<BookDetails> { key ->
                BookDetailsScreen(
                    bookId = key.bookId,
                    onBackClick = { navState.goBack() },
                )
            }
        },
    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            if (navState.isAtTopLevel) {
                BookShelfNavigationBar(
                    items = topLevelDestinations.map { destination ->
                        BookShelfNavigationItem(
                            id = destination.id,
                            label = destination.label,
                            icon = destination.unselectedIcon,
                            selectedIcon = destination.selectedIcon,
                        )
                    },
                    selectedItemId = topLevelDestinations.first { it.key == navState.currentTab }.id,
                    onItemSelected = { id ->
                        navState.selectTab(topLevelDestinations.first { it.id == id }.key)
                    },
                )
            }
        },
    ) { innerPadding ->
        NavDisplay(
            entries = entries,
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
            onBack = { navState.goBack() },
        )
    }
}
