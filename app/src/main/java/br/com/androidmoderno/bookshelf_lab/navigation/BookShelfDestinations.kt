package br.com.androidmoderno.bookshelf_lab.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

/**
 * Destinos do BookShelf para o Jetpack Navigation 3. Cada um implementa [NavKey] — o
 * marcador que tipa as entradas do back stack, sem rota em String — e é `@Serializable`
 * para que o back stack (via `rememberNavBackStack`) sobreviva a rotação e morte de
 * processo.
 *
 * Destinos de primeiro nível (abas da bottom bar). Cada um tem sua própria back stack
 * independente — trocar de aba não perde o histórico de navegação das outras abas.
 */
@Serializable
sealed interface BottomNavKey : NavKey {
    @Serializable
    data object Discover : BottomNavKey

    @Serializable
    data object Library : BottomNavKey

    companion object {
        val entries = listOf(Discover, Library)
    }
}

/**
 * Leva apenas o identificador do livro — nunca o objeto completo, um DTO ou uma
 * entidade de banco. Empilhado dentro da back stack da aba de onde foi aberto.
 */
@Serializable
data class BookDetails(val bookId: String) : NavKey
