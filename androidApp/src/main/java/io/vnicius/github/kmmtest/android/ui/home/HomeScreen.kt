package io.vnicius.github.kmmtest.android.ui.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import io.vnicius.github.kmmtest.android.R
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

        pokemonsItems.apply {
            when {
                loadState.append is LoadState.Loading ||
                        loadState.refresh is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {
                    (loadState.refresh as? LoadState.Error)?.error?.localizedMessage?.let {
                        item { ErrorItem(message = it) }
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingItem(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier
            .fillMaxWidth()
            .size(32.dp)
            .padding(16.dp)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun ErrorItem(message: String, modifier: Modifier = Modifier) {
    Text(
        text = message,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        color = Color.Red,
        textAlign = TextAlign.Center,
        fontStyle = FontStyle.Italic
    )
}

@Composable
fun PokemonItem(pokemon: Pokemon, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = Modifier
            .clickable {
                Log.d("pokemon", "pokemon: $pokemon")
            }
            .padding(horizontal = 10.dp, vertical = 4.dp)
            .fillMaxWidth()) {
            Image(
                painter = rememberImagePainter(data = pokemon.spriteUrl) {
                    placeholder(R.drawable.ic_pokeball)
                    error(R.drawable.ic_pokeball)
                    crossfade(true)
                },
                contentDescription = pokemon.name,
                modifier = Modifier.size(50.dp)
            )
            Text(
                text = pokemon.name.replaceFirstChar { it.titlecase() },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 12.dp)
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

