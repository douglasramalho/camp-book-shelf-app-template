package br.com.androidmoderno.bookshelf.network

import android.util.Log
import br.com.androidmoderno.bookshelf.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

const val OPEN_LIBRARY_BASE_URL = "https://openlibrary.org"

const val DEFAULT_OPEN_LIBRARY_USER_AGENT =
    "BookShelfLab/1.0 (br.com.androidmoderno.bookshelf; contato: )"

/**
 * Cria um [HttpClient] Ktor configurado para consumir a OpenLibrary API.
 *
 * @param userAgent identifica o app nas requisições; sobrescreva em testes ou builds
 * diferentes sem precisar tocar na configuração do client.
 */
fun createOpenLibraryHttpClient(
    userAgent: String = DEFAULT_OPEN_LIBRARY_USER_AGENT,
): HttpClient = HttpClient(Android) {
    expectSuccess = true

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                Log.d("BookShelfHttp", message)
            }
        }

        level = if (BuildConfig.DEBUG) {
            LogLevel.ALL
        } else {
            LogLevel.NONE
        }
    }

    install(ContentNegotiation) {
        json(
            Json {
                ignoreUnknownKeys = true
                isLenient = true
                coerceInputValues = true
            }
        )
    }

    defaultRequest {
        url(OPEN_LIBRARY_BASE_URL)
        header(HttpHeaders.UserAgent, userAgent)
    }
}
