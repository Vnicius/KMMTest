package io.vnicius.github.kmmtest.api.rest

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*


object RestManager {
    val client = httpClient {
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
            }
            serializer = KotlinxSerializer(json)
        }
    }
}

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient
