package br.com.androidmoderno.bookshelf.core.ui.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import br.com.androidmoderno.bookshelf.core.ui.designsystem.theme.BookShelfTheme

/**
 * Estado de carregamento reutilizável. Preenche o espaço disponível e centraliza o
 * indicador — como o tamanho vem do [modifier] do chamador, funciona tanto como estado
 * de tela inteira quanto embutido numa área menor (por exemplo, o rodapé de uma lista).
 */
@Composable
fun LoadingContent(
    modifier: Modifier = Modifier,
    message: String? = null,
) {
    val spacing = BookShelfTheme.spacing

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(spacing.spacing12),
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 3.dp,
            )
            if (message != null) {
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun LoadingContentPreview() {
    BookShelfTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.height(240.dp),
        ) {
            LoadingContent(message = "Buscando livros…")
        }
    }
}
