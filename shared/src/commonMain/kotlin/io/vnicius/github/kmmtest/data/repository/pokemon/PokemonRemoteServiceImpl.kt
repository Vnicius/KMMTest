package io.vnicius.github.kmmtest.data.repository.pokemon

import io.ktor.client.request.*
import io.vnicius.github.kmmtest.api.APIConstants
import io.vnicius.github.kmmtest.api.rest.RestManager
import io.vnicius.github.kmmtest.data.model.Pokemon
import io.vnicius.github.kmmtest.data.model.PokemonItemResponse
import io.vnicius.github.kmmtest.data.model.PokemonsListResponse


/**
 * Created by Vinícius Veríssimo on 09/11/21.
 * github: @vnicius
 * vinicius.matheus252@gmail.com
 */
class PokemonRemoteServiceImpl(restManager: RestManager): PokemonRemoteService {

    private val client = restManager.client

    override suspend fun getPokemons(limit: Int, offset: Int): List<Pokemon>? {
        val response : PokemonsListResponse = client.get("${APIConstants.BASE_URL}/pokemon") {
            parameter("limit", limit)
            parameter("offset", offset)
        }
        val pokemonsItems: List<PokemonItemResponse>? = response.results
        val pokemons = pokemonsItems?.map { pokemon ->
            val id = pokemon.url?.split("/")?.lastOrNull { it.isNotBlank() }?.toLong() ?: 0L
            Pokemon(id, pokemon.name, "${APIConstants.BASE_SPRITE_URL}/${id}.png")
        }

        return pokemons
    }
}
