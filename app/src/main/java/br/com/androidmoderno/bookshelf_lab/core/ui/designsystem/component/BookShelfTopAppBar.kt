package br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import br.com.androidmoderno.bookshelf_lab.R
import br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.theme.BookShelfTheme

/**
 * Topo de tela reutilizável. Não conhece `NavController`: quem decide o que "voltar"
 * significa (pop back stack, fechar um modal, etc.) é quem chama [onBackClick].
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookShelfTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    showBackButton: Boolean = false,
    onBackClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        modifier = modifier,
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.cd_top_app_bar_back),
                    )
                }
            }
        },
        actions = actions,
    )
}

@PreviewLightDark
@Composable
private fun BookShelfTopAppBarPreview() {
    BookShelfTheme {
        Surface(color = MaterialTheme.colorScheme.surface) {
            Column {
                BookShelfTopAppBar(title = "Descobrir")
                BookShelfTopAppBar(
                    title = "Detalhes do livro",
                    showBackButton = true,
                    onBackClick = {},
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = stringResource(R.string.cd_favorite_remove),
                            )
                        }
                    },
                )
            }
        }
    }
}
