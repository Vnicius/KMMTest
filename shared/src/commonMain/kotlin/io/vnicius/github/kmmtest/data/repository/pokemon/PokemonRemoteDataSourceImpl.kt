package io.vnicius.github.kmmtest.data.repository.pokemon


/**
 * Created by Vinícius Veríssimo on 09/11/21.
 * github: @vnicius
 * vinicius.matheus252@gmail.com
 */
class PokemonRemoteDataSourceImpl(private val pokemonRemoteService: PokemonRemoteService):
    PokemonRemoteDataSource, PokemonRemoteService by pokemonRemoteService {
}
