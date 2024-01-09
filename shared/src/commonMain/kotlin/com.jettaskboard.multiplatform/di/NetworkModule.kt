package com.jettaskboard.multiplatform.di

import com.jettaskboard.multiplatform.BuildKonfig
import com.jettaskboard.multiplatform.data.util.Constants
import com.jettaskboard.multiplatform.util.logger.initLogger
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.dsl.module

@OptIn(ExperimentalSerializationApi::class)
val networkModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        isLenient = true
                        explicitNulls = false
                    }
                )
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.i(tag = "HTTP Client", message = message)
                    }
                }
                level = LogLevel.BODY
            }.also { initLogger() }

            defaultRequest {
                url {
                    url(Constants.BASE_URL)
                    protocol = URLProtocol.HTTPS
                    parameters.append(Constants.PARAM_AUTH_CLIENT_ID, BuildKonfig.UNSPLASH_API_TOKEN)
                }
            }
        }
    }
}
