package br.com.androidmoderno.bookshelf_lab.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import br.com.androidmoderno.bookshelf_lab.R

/**
 * Informação puramente visual de um destino de primeiro nível (aba). Não conhece
 * NavController, back stack ou callback de navegação — quem monta a bottom bar decide
 * o que fazer com [key] ao selecionar o item.
 */
data class TopLevelDestination(
    val key: BottomNavKey,
    val id: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

/** Destinos de primeiro nível do BookShelf: Descobrir e Biblioteca. */
@Composable
fun rememberTopLevelDestinations(): List<TopLevelDestination> {
    val discoverLabel = stringResource(R.string.top_level_destination_discover)
    val libraryLabel = stringResource(R.string.top_level_destination_library)

    return remember(discoverLabel, libraryLabel) {
        listOf(
            TopLevelDestination(
                key = BottomNavKey.Discover,
                id = "discover",
                label = discoverLabel,
                selectedIcon = Icons.Filled.Explore,
                unselectedIcon = Icons.Outlined.Explore,
            ),
            TopLevelDestination(
                key = BottomNavKey.Library,
                id = "library",
                label = libraryLabel,
                selectedIcon = Icons.Filled.AutoStories,
                unselectedIcon = Icons.Outlined.AutoStories,
            ),
        )
    }
}
