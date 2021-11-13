package io.vnicius.github.kmmtest.android.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import io.vnicius.github.kmmtest.android.ui.theme.Shapes
import io.vnicius.github.kmmtest.data.model.Pokemon
import kotlinx.coroutines.flow.Flow


/**
 * Created by Vinícius Veríssimo on 09/11/21.
 * github: @vnicius
 * vinicius.matheus252@gmail.com
 */
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel(), modifier: Modifier = Modifier) {
    Surface(Modifier.fillMaxSize()) {
        HomeContent(
            pokemons = homeViewModel.createPokemonsPaging()
        )
    }
}

@Composable
fun HomeContent(pokemons: Flow<PagingData<Pokemon>>, modifier: Modifier = Modifier) {
    PokemonsList(pokemons)
}

@Composable
fun PokemonsList(pokemons: Flow<PagingData<Pokemon>>, modifier: Modifier = Modifier) {
    val pokemonsItems = pokemons.collectAsLazyPagingItems()

    LazyColumn {
        items(pokemonsItems.itemCount) { index ->
            pokemonsItems[index]?.let {
                PokemonItem(it)
            }
        }
    }
}

@Composable
fun PokemonItem(pokemon: Pokemon, modifier: Modifier = Modifier) {
    Surface(modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
        Row {
            Image(
                painter = rememberImagePainter(data = pokemon.spriteUrl),
                contentDescription = pokemon.name,
                modifier = Modifier.size(50.dp)
            )
            Spacer(
                modifier = Modifier
                    .width(12.dp)
                    .fillMaxHeight()
            )
            Text(
                text = pokemon.name.capitalize(),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview
@Composable
fun PreviewPokemonItem() {
    PokemonItem(
        Pokemon(
            1,
            "Pokemon 1",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"
        )
    )
}

