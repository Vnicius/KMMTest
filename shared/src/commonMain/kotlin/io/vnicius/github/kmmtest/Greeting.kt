package io.vnicius.github.kmmtest

import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.Serializable

@Serializable
data class PokemonsListResponse(
    val count: Long,
    val next: String?,
    val previous: String?,
    val results: List<PokemonIndex>?
)

@Serializable
data class PokemonIndex(val name: String, val url: String)

class Greeting {
    private val client = httpClient {
        install(JsonFeature) {
            val json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
            }
            serializer = KotlinxSerializer(json)
        }
    }

    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }

    suspend fun getPokemons(): List<PokemonIndex>? {
        val response : PokemonsListResponse = client.get("https://pokeapi.co/api/v2/pokemon")

        return response.results
    }
}
