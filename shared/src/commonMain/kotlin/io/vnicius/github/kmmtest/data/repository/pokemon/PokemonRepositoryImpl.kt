package io.vnicius.github.kmmtest.data.repository.pokemon

import io.vnicius.github.kmmtest.data.model.Pokemon


/**
 * Created by Vinícius Veríssimo on 09/11/21.
 * github: @vnicius
 * vinicius.matheus252@gmail.com
 */
class PokemonRepositoryImpl(private val pokemonRemoteDataSource: PokemonRemoteDataSource): PokemonRepository {

    override suspend fun getPokemons(): List<Pokemon>? =
        pokemonRemoteDataSource.getPokemons()
}
