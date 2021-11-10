package io.vnicius.github.kmmtest.android.ui.home

import io.vnicius.github.kmmtest.data.model.Pokemon


/**
 * Created by Vinícius Veríssimo on 09/11/21.
 * github: @vnicius
 * vinicius.matheus252@gmail.com
 */
data class HomeViewState(
    val pokemons: List<Pokemon> = emptyList(),
    val isLoading: Boolean = false
)
