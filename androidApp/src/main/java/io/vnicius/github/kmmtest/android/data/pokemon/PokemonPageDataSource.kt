package io.vnicius.github.kmmtest.android.data.pokemon

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.vnicius.github.kmmtest.data.model.Pokemon
import io.vnicius.github.kmmtest.data.repository.pokemon.PokemonRepository


/**
 * Created by Vinícius Veríssimo on 10/11/21.
 * github: @vnicius
 * vinicius.matheus252@gmail.com
 */
class PokemonPageDataSource(private val pokemonRepository: PokemonRepository) :
    PagingSource<Int, Pokemon>() {

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            val dataSize = anchorPage?.data?.size ?: return@let null

            anchorPage.prevKey?.plus(dataSize) ?: anchorPage.nextKey?.minus(dataSize)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> =
        try {
            val offset = params.key ?: 0
            val result = pokemonRepository.getPokemons(PAGE_SIZE, offset) ?: emptyList()
            val nextPage = offset + result.size

            LoadResult.Page(
                data = result,
                prevKey = null,
                nextKey = nextPage.takeIf { it != offset }
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    companion object {
        const val PAGE_SIZE = 20
    }
}
