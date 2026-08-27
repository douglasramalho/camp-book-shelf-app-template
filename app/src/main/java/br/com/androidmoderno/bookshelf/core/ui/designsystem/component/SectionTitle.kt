package br.com.androidmoderno.bookshelf.core.ui.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark

import br.com.androidmoderno.bookshelf.core.ui.designsystem.theme.BookShelfTheme

/**
 * Título visual de uma seção dentro de uma tela (ex.: "Sinopse", "Assuntos"). A ação é
 * totalmente opcional: só aparece quando [actionLabel] e [onActionClick] são fornecidos
 * juntos.
 */
@Composable
fun SectionTitle(
    title: String,
    modifier: Modifier = Modifier,
    actionLabel: String? = null,
    onActionClick: (() -> Unit)? = null,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        if (actionLabel != null && onActionClick != null) {
            TextButton(onClick = onActionClick) {
                Text(text = actionLabel, style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun SectionTitlePreview() {
    BookShelfTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SectionTitle(title = "Sinopse")
        }
    }
}

@PreviewLightDark
@Composable
private fun SectionTitleWithActionPreview() {
    BookShelfTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SectionTitle(
                title = "Resultados",
                actionLabel = "Ver mais",
                onActionClick = {},
            )
        }
    }
}
