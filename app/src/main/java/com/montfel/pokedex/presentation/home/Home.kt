package com.montfel.pokedex.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.montfel.pokedex.R
import com.montfel.pokedex.presentation.navigation.BottomSheetFilter
import com.montfel.pokedex.presentation.theme.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Home(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    onClick: (BottomSheetFilter) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    var text by remember { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        viewModel.showAllPokemons()
    }

    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
        item {
            TopBar(onClick = onClick)
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
            TextField(
                value = text,
                onValueChange = {
                    text = it
                    viewModel.searchPokemon(it)
                },
                maxLines = 1,
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
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            )
        }
        uiState.pokemonList?.let { pokemonList ->
            items(
                items = pokemonList,
                key = { it.id }
            ) { pokemon ->
                PokemonCard(pokemon = pokemon) {
                    navController.navigate("profile/${pokemon.id}")
                }
            }
        }
    }
}
