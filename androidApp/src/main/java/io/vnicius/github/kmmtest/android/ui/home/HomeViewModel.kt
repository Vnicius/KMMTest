package io.vnicius.github.kmmtest.android.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.vnicius.github.kmmtest.api.rest.RestManager
import io.vnicius.github.kmmtest.data.model.Pokemon
import io.vnicius.github.kmmtest.data.repository.pokemon.PokemonRemoteDataSourceImpl
import io.vnicius.github.kmmtest.data.repository.pokemon.PokemonRemoteServiceImpl
import io.vnicius.github.kmmtest.data.repository.pokemon.PokemonRepository
import io.vnicius.github.kmmtest.data.repository.pokemon.PokemonRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch


/**
 * Created by Vinícius Veríssimo on 09/11/21.
 * github: @vnicius
 * vinicius.matheus252@gmail.com
 */
class HomeViewModel: ViewModel() {
    private val pokemonRepository: PokemonRepository by lazy {
        PokemonRepositoryImpl(PokemonRemoteDataSourceImpl(PokemonRemoteServiceImpl(RestManager)))
    }
    private val pokemons = MutableStateFlow<List<Pokemon>>(emptyList())
    private val isRefreshing = MutableStateFlow<Boolean>(false)
    private val _state = MutableStateFlow<HomeViewState>(HomeViewState())

    val state: StateFlow<HomeViewState>
        get() = _state

    init {
        viewModelScope.launch {
            combine(
                pokemons,
                isRefreshing
            ) { pokemons, isRefreshing ->
                HomeViewState(
                    pokemons,
                    isRefreshing
                )
            }.collect {
                _state.value = it
            }
        }

        fetchPokemons()
    }

    fun fetchPokemons() {
        viewModelScope.launch {
            kotlin.runCatching {
                isRefreshing.value = true
                pokemonRepository.getPokemons()?.let {
                    pokemons.value = it
                }
            }

            isRefreshing.value = false
        }
    }
}
