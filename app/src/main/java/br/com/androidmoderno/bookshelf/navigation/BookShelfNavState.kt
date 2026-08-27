package br.com.androidmoderno.bookshelf.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator

/**
 * Estado de navegação com uma back stack independente por aba da bottom bar. Trocar de
 * aba não perde o histórico nem o estado (scroll, formulários, ViewModels) das outras
 * abas — cada uma continua viva em segundo plano.
 */
@Stable
class BookShelfNavState(
    val backStacks: Map<BottomNavKey, NavBackStack<NavKey>>,
    private val currentTabState: MutableState<BottomNavKey>,
) {
    val currentTab: BottomNavKey get() = currentTabState.value

    /** Verdadeiro quando a aba atual está na sua raiz — usado para exibir a bottom bar. */
    val isAtTopLevel: Boolean get() = currentBackStack.size <= 1

    private val currentBackStack: NavBackStack<NavKey> get() = backStacks.getValue(currentTab)

    fun selectTab(tab: BottomNavKey) {
        currentTabState.value = tab
    }

    fun push(key: NavKey) {
        currentBackStack.add(key)
    }

    /** Volta na aba atual ou, na raiz, cai para "Descobrir" ("exit through home"). */
    fun goBack(): Boolean = when {
        currentBackStack.size > 1 -> {
            currentBackStack.removeLastOrNull()
            true
        }

        currentTab != BottomNavKey.Discover -> {
            currentTabState.value = BottomNavKey.Discover
            true
        }

        else -> false
    }
}

private val BottomNavKeySaver = Saver<BottomNavKey, String>(
    save = { it::class.qualifiedName },
    restore = { qualifiedName ->
        BottomNavKey.entries.firstOrNull { it::class.qualifiedName == qualifiedName } ?: BottomNavKey.Discover
    },
)

@Composable
fun rememberBookShelfNavState(): BookShelfNavState {
    val discoverBackStack = rememberNavBackStack(BottomNavKey.Discover)
    val libraryBackStack = rememberNavBackStack(BottomNavKey.Library)

    val currentTabState = rememberSaveable(stateSaver = BottomNavKeySaver) {
        mutableStateOf(BottomNavKey.Discover)
    }

    return remember(discoverBackStack, libraryBackStack, currentTabState) {
        BookShelfNavState(
            backStacks = linkedMapOf(
                BottomNavKey.Discover to discoverBackStack,
                BottomNavKey.Library to libraryBackStack,
            ),
            currentTabState = currentTabState,
        )
    }
}

/**
 * Decora a back stack de toda aba a cada recomposição (mantendo o SaveableStateHolder e
 * o ViewModelStore de cada uma vivos mesmo enquanto escondida), e só então achata a aba
 * "Descobrir" (home) + a aba atual na lista que o [androidx.navigation3.ui.NavDisplay]
 * renderiza — o padrão "exit through home".
 */
@Composable
fun rememberBookShelfNavEntries(
    navState: BookShelfNavState,
    entryProvider: (NavKey) -> NavEntry<NavKey>,
): List<NavEntry<NavKey>> {
    val decoratedEntries = navState.backStacks.mapValues { (_, stack) ->
        rememberDecoratedNavEntries(
            backStack = stack,
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
            entryProvider = entryProvider,
        )
    }

    val activeTabs = if (navState.currentTab == BottomNavKey.Discover) {
        listOf(BottomNavKey.Discover)
    } else {
        listOf(BottomNavKey.Discover, navState.currentTab)
    }

    return activeTabs.flatMap { decoratedEntries.getValue(it) }
}
