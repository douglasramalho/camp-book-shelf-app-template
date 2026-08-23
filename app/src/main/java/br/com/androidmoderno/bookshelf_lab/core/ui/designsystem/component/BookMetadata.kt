package br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Numbers
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.PreviewLightDark
import br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.theme.BookShelfTheme

/**
 * Metadado curto exibido com ícone, rótulo e valor — ano, quantidade de edições,
 * idioma, etc. O rótulo funciona como legenda acima do valor.
 */
@Composable
fun BookMetadata(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    val spacing = BookShelfTheme.spacing
    val dimensions = BookShelfTheme.dimensions

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.spacing8),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(dimensions.iconSizeSmall),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Column {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun BookMetadataPreview() {
    BookShelfTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(BookShelfTheme.spacing.spacing24),
            ) {
                BookMetadata(icon = Icons.Outlined.CalendarToday, label = "Ano", value = "1979")
                BookMetadata(icon = Icons.Outlined.Numbers, label = "Edições", value = "128")
                BookMetadata(icon = Icons.Outlined.Language, label = "Idioma", value = "Português")
            }
        }
    }
}
