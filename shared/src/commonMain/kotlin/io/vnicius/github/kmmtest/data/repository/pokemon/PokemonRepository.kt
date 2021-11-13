package io.vnicius.github.kmmtest.data.repository.pokemon

import io.vnicius.github.kmmtest.data.model.Pokemon


/**
 * Created by Vinícius Veríssimo on 09/11/21.
 * github: @vnicius
 * vinicius.matheus252@gmail.com
 */
interface PokemonRepository {
    suspend fun getPokemons(limit: Int, offset: Int): List<Pokemon>?
}
