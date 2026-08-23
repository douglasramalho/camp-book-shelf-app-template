package br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.theme.BookShelfTheme

/**
 * Chip visual de status de leitura (Nenhum, Quero ler, Lendo, Concluído). O status em
 * si não é modelado aqui — [label] e [selected] são simples valores de UI, sem enum de
 * domínio. O ícone de check no estado selecionado evita depender só de cor para indicar
 * a seleção.
 */
@Composable
fun ReadingStatusChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = { Text(text = label, style = MaterialTheme.typography.labelMedium) },
        modifier = modifier,
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    modifier = Modifier.size(FilterChipDefaults.IconSize),
                )
            }
        } else {
            null
        },
    )
}

@PreviewLightDark
@Composable
private fun ReadingStatusChipPreview() {
    BookShelfTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Row(
                modifier = Modifier.padding(BookShelfTheme.spacing.spacing16),
                horizontalArrangement = Arrangement.spacedBy(BookShelfTheme.spacing.spacing8),
            ) {
                ReadingStatusChip(label = "Nenhum", selected = false, onClick = {})
                ReadingStatusChip(label = "Quero ler", selected = false, onClick = {})
                ReadingStatusChip(label = "Lendo", selected = true, onClick = {})
                ReadingStatusChip(label = "Concluído", selected = false, onClick = {})
            }
        }
    }
}
