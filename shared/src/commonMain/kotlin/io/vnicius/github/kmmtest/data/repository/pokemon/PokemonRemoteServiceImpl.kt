package io.vnicius.github.kmmtest.data.repository.pokemon

import io.ktor.client.request.*
import io.vnicius.github.kmmtest.api.APIConstants
import io.vnicius.github.kmmtest.api.rest.RestManager
import io.vnicius.github.kmmtest.data.model.Pokemon
import io.vnicius.github.kmmtest.data.model.PokemonDetailsResponse
import io.vnicius.github.kmmtest.data.model.PokemonItemResponse
import io.vnicius.github.kmmtest.data.model.PokemonsListResponse


/**
 * Created by Vinícius Veríssimo on 09/11/21.
 * github: @vnicius
 * vinicius.matheus252@gmail.com
 */
class PokemonRemoteServiceImpl(restManager: RestManager): PokemonRemoteService {

    private val client = restManager.client

    override suspend fun getPokemons(): List<Pokemon>? {
        val response : PokemonsListResponse = client.get("${APIConstants.BASE_URL}/pokemon")
        val pokemonsItems: MutableList<PokemonItemResponse>? = response.results?.toMutableList()
        val pokemons = pokemonsItems?.mapNotNull { pokemon ->
            val url = pokemon.url ?: return@mapNotNull null
            val pokemonDetails: PokemonDetailsResponse = client.get(url)
            Pokemon(pokemon.name, pokemonDetails.sprites?.frontDefault)
        }

        return pokemons
    }
}
