package com.montfel.pokedex.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.montfel.pokedex.R
import com.montfel.pokedex.presentation.theme.*

@Composable
fun Home(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val text = remember { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        viewModel.showAllPokemons()
    }

    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
        item {
            TopBar()
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.applicationTitle,
                color = Gray17,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.subtitle),
                style = MaterialTheme.typography.description,
                color = Gray74,
            )
            CustomTextField(viewModel, text)
        }
        if (text.value.isBlank()) {
            uiState.pokemonList?.let {
                items(it) { pokemon ->
                    PokemonCard(pokemon = pokemon) {
                        navController.navigate("profile/${pokemon.id}")
                    }
                }
            }
        } else {
            item {
                uiState.pokemon?.let { pokemon ->
                    PokemonCard(pokemon = pokemon) {
                        navController.navigate("profile/${pokemon.id}")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomTextField(viewModel: HomeViewModel = hiltViewModel(), text: MutableState<String>) {
    TextField(
        value = text.value,
        onValueChange = {
            text.value = it
            if (it.isNotBlank()) {
                viewModel.searchPokemon(it)
            }
        },
        shape = RoundedCornerShape(10.dp),
        textStyle = MaterialTheme.typography.description,
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
            )
        },
        colors = textFieldColors(
            backgroundColor = GrayF2,
            textColor = Gray17,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = TypePsychic,
        ),
        placeholder = {
            Text(
                text = stringResource(id = R.string.placeholder_textfield),
                style = MaterialTheme.typography.description,
                color = Gray74
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp)
    )
}
