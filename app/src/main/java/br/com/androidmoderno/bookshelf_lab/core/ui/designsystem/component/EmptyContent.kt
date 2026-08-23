package br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.theme.BookShelfTheme

/**
 * Estado vazio reutilizável — resultado de busca sem itens, biblioteca sem livros, etc.
 * A ação é totalmente opcional: só aparece quando [actionLabel] e [onActionClick] são
 * fornecidos juntos.
 */
@Composable
fun EmptyContent(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.AutoMirrored.Outlined.MenuBook,
    actionLabel: String? = null,
    onActionClick: (() -> Unit)? = null,
) {
    val spacing = BookShelfTheme.spacing
    val dimensions = BookShelfTheme.dimensions

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(spacing.spacing24),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(dimensions.iconSizeLarge),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(modifier = Modifier.height(spacing.spacing16))
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(spacing.spacing8))
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )
        if (actionLabel != null && onActionClick != null) {
            Spacer(modifier = Modifier.height(spacing.spacing24))
            Button(onClick = onActionClick) {
                Text(text = actionLabel)
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun EmptyContentPreview() {
    BookShelfTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            EmptyContent(
                title = "Nenhum resultado",
                message = "Não encontramos livros para essa pesquisa. Tente outro título, autor ou assunto.",
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun EmptyContentWithActionPreview() {
    BookShelfTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            EmptyContent(
                title = "Sua biblioteca está vazia",
                message = "Livros que você salvar aparecem aqui.",
                actionLabel = "Descobrir livros",
                onActionClick = {},
            )
        }
    }
}
