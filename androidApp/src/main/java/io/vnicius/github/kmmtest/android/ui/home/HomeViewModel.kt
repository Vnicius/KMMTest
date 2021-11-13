package io.vnicius.github.kmmtest.android.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import io.vnicius.github.kmmtest.android.data.pokemon.PokemonPageDataSource
import io.vnicius.github.kmmtest.api.rest.RestManager
import io.vnicius.github.kmmtest.data.model.Pokemon
import io.vnicius.github.kmmtest.data.repository.pokemon.PokemonRemoteDataSourceImpl
import io.vnicius.github.kmmtest.data.repository.pokemon.PokemonRemoteServiceImpl
import io.vnicius.github.kmmtest.data.repository.pokemon.PokemonRepository
import io.vnicius.github.kmmtest.data.repository.pokemon.PokemonRepositoryImpl
import kotlinx.coroutines.flow.Flow


/**
 * Created by Vinícius Veríssimo on 09/11/21.
 * github: @vnicius
 * vinicius.matheus252@gmail.com
 */
class HomeViewModel: ViewModel() {
    private val pokemonRepository: PokemonRepository by lazy {
        PokemonRepositoryImpl(PokemonRemoteDataSourceImpl(PokemonRemoteServiceImpl(RestManager)))
    }

    fun createPokemonsPaging(): Flow<PagingData<Pokemon>> =
        Pager(PagingConfig(pageSize = PokemonPageDataSource.PAGE_SIZE)) {
            PokemonPageDataSource(pokemonRepository)
        }.flow.cachedIn(viewModelScope)
}
