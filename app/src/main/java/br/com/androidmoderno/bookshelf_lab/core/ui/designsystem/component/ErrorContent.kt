package br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import br.com.androidmoderno.bookshelf_lab.R
import br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.theme.BookShelfTheme

/**
 * Estado de erro reutilizável. Recebe só [title]/[message] já traduzidos para o
 * usuário — nunca uma exceção — para que a camada de UI nunca precise saber o que é
 * um `Throwable`. O ícone e os textos ficam neutros (sem vermelho de destaque) para
 * manter a aparência acolhedora pedida no design system.
 */
@Composable
fun ErrorContent(
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    retryLabel: String = stringResource(R.string.error_content_retry_label),
    onRetryClick: (() -> Unit)? = null,
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
            imageVector = Icons.Outlined.ErrorOutline,
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
        if (onRetryClick != null) {
            Spacer(modifier = Modifier.height(spacing.spacing24))
            Button(onClick = onRetryClick) {
                Text(text = retryLabel)
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun ErrorContentPreview() {
    BookShelfTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ErrorContent(
                title = "Não foi possível carregar",
                message = "Verifique sua conexão e tente novamente.",
                onRetryClick = {},
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun ErrorContentWithoutRetryPreview() {
    BookShelfTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ErrorContent(
                title = "Não foi possível carregar",
                message = "Tente novamente mais tarde.",
            )
        }
    }
}
