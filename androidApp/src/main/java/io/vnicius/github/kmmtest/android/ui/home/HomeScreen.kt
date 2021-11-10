package io.vnicius.github.kmmtest.android.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import io.vnicius.github.kmmtest.data.model.Pokemon


/**
 * Created by Vinícius Veríssimo on 09/11/21.
 * github: @vnicius
 * vinicius.matheus252@gmail.com
 */
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel(), modifier: Modifier = Modifier) {
    val viewState by homeViewModel.state.collectAsState()

    Surface(Modifier.fillMaxSize()) {
        HomeContent(
            pokemons = viewState.pokemons,
            isLoading = viewState.isLoading
        )
    }
}

@Composable
fun HomeContent(pokemons: List<Pokemon>, isLoading: Boolean, modifier: Modifier = Modifier) {
    PokemonsList(pokemons)

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun PokemonsList(pokemons: List<Pokemon>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(items = pokemons) { pokemon ->
            PokemonItem(pokemon)
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
            "Pokemon 1",
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"
        )
    )
}

@Preview
@Composable
fun PreviewHomeContent() {
    HomeContent(
        listOf(
            Pokemon(
                "Pokemon 1",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"
            ),
            Pokemon(
                "Pokemon 2",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"
            ),
            Pokemon(
                "Pokemon 3",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/35.png"
            )
        ),
        false
    )
}


