package br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.preview

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Numbers
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import br.com.androidmoderno.bookshelf_lab.R
import br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.component.BookCover
import br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.component.BookCoverSize
import br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.component.BookMetadata
import br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.component.BookShelfTopAppBar
import br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.component.ReadingStatusChip
import br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.component.SectionTitle
import br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.theme.BookShelfTheme

/**
 * Demonstração da tela de detalhes de um livro, combinando capa grande, metadados,
 * status de leitura e assuntos. Dados locais de preview, sem tela real nem navegação.
 */
@OptIn(ExperimentalMaterial3Api::class)
@PreviewLightDark
@Composable
private fun BookDetailsPreview() {
    val book = PreviewBooks.first()
    val spacing = BookShelfTheme.spacing

    BookShelfTheme {
        Scaffold(
            topBar = {
                BookShelfTopAppBar(
                    title = book.title,
                    showBackButton = true,
                    onBackClick = {},
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                contentDescription = stringResource(R.string.cd_favorite_remove),
                                tint = MaterialTheme.colorScheme.tertiary,
                            )
                        }
                    },
                )
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(spacing.spacing16),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(spacing.spacing16),
            ) {
                BookCover(
                    imageUrl = book.coverUrl,
                    contentDescription = "Capa de ${book.title}",
                    size = BookCoverSize.Large,
                )
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = book.authors,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                )

                SectionTitle(title = "Status de leitura", modifier = Modifier.fillMaxWidth())
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(spacing.spacing8),
                ) {
                    ReadingStatusChip(label = "Nenhum", selected = false, onClick = {})
                    ReadingStatusChip(label = "Quero ler", selected = false, onClick = {})
                    ReadingStatusChip(label = "Lendo", selected = true, onClick = {})
                    ReadingStatusChip(label = "Concluído", selected = false, onClick = {})
                }

                SectionTitle(title = "Detalhes", modifier = Modifier.fillMaxWidth())
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(spacing.spacing24),
                ) {
                    BookMetadata(
                        icon = Icons.Outlined.CalendarToday,
                        label = "Ano",
                        value = book.firstPublishedYear?.toString() ?: "—",
                    )
                    BookMetadata(icon = Icons.Outlined.Numbers, label = "Edições", value = "128")
                    BookMetadata(icon = Icons.Outlined.Language, label = "Idioma", value = "Português")
                }

                SectionTitle(title = "Sinopse", modifier = Modifier.fillMaxWidth())
                Text(
                    text = PreviewBookDescription,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )

                SectionTitle(title = "Assuntos", modifier = Modifier.fillMaxWidth())
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(spacing.spacing8),
                ) {
                    PreviewSubjects.forEach { subject ->
                        AssistChip(onClick = {}, label = { Text(subject) })
                    }
                }
            }
        }
    }
}
