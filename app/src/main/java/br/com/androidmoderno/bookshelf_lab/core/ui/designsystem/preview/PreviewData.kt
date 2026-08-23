package br.com.androidmoderno.bookshelf_lab.core.ui.designsystem.preview

/**
 * Dados locais usados apenas pelas previews do design system — nenhuma chamada de
 * rede, ViewModel ou Repository envolvida.
 */
internal data class PreviewBook(
    val title: String,
    val authors: String,
    val coverUrl: String?,
    val firstPublishedYear: Int?,
    val isFavorite: Boolean,
)

internal val PreviewBooks = listOf(
    PreviewBook(
        title = "O Guia do Mochileiro das Galáxias",
        authors = "Douglas Adams",
        coverUrl = null,
        firstPublishedYear = 1979,
        isFavorite = true,
    ),
    PreviewBook(
        title = "Fundação",
        authors = "Isaac Asimov",
        coverUrl = null,
        firstPublishedYear = null,
        isFavorite = false,
    ),
    PreviewBook(
        title = "Compilação de Contos Populares Sem Autoria Catalogada na Open Library",
        authors = "",
        coverUrl = null,
        firstPublishedYear = 1888,
        isFavorite = false,
    ),
)

internal val PreviewSubjects = listOf("Ficção científica", "Clássico", "Aventura", "Humor")

internal const val PreviewBookDescription = "Arthur Dent é um homem comum cuja casa está prestes " +
    "a ser demolida para dar lugar a uma via expressa — no mesmo dia em que a Terra inteira é " +
    "destruída para dar lugar a uma via expressa intergaláctica. Resgatado por seu amigo Ford " +
    "Prefect, ele embarca numa jornada absurda pelo espaço."
