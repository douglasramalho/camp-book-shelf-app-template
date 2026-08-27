package br.com.androidmoderno.bookshelf.core.ui.designsystem.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.PreviewLightDark
import br.com.androidmoderno.bookshelf.core.ui.designsystem.theme.BookShelfTheme

/**
 * Modelo puramente visual de um item de navegação — sem rota, sem `NavGraph`, sem
 * nada do Navigation Compose. Quem monta a tela mapeia `id` para uma rota real.
 */
data class BookShelfNavigationItem(
    val id: String,
    val label: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector = icon,
)

/**
 * Bottom navigation visual para Descobrir/Biblioteca. Não conhece Navigation Compose:
 * [selectedItemId] e [onItemSelected] trabalham só com o `id` de [BookShelfNavigationItem].
 */
@Composable
fun BookShelfNavigationBar(
    items: List<BookShelfNavigationItem>,
    selectedItemId: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(modifier = modifier) {
        items.forEach { item ->
            val selected = item.id == selectedItemId
            NavigationBarItem(
                selected = selected,
                onClick = { onItemSelected(item.id) },
                icon = {
                    Icon(
                        imageVector = if (selected) item.selectedIcon else item.icon,
                        contentDescription = null,
                    )
                },
                label = { Text(item.label) },
            )
        }
    }
}

private val PreviewItems = listOf(
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

@PreviewLightDark
@Composable
private fun BookShelfNavigationBarPreview() {
    BookShelfTheme {
        Surface(color = MaterialTheme.colorScheme.surface) {
            BookShelfNavigationBar(
                items = PreviewItems,
                selectedItemId = "discover",
                onItemSelected = {},
            )
        }
    }
}
