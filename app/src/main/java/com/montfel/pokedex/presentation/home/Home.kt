package com.montfel.pokedex.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.montfel.pokedex.R
import com.montfel.pokedex.presentation.theme.*
import kotlinx.coroutines.runBlocking

@Composable
fun Home(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val assetHelper = LocalAssetHelper.current
    val text = remember { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllPokemons()
    }

    LazyColumn(modifier = Modifier.padding(horizontal = 24.dp)) {
        item {
            TopBar()
        }
        item {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h2,
                color = Gray17,
            )
        }
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {
            Text(
                text = stringResource(id = R.string.subtitle),
                style = MaterialTheme.typography.h5,
                color = Gray74,
            )
        }
        item {
            CustomTextField(viewModel, text)
        }
        items(if (text.value.isBlank()) uiState.results else uiState.pokemon, key = { it.id }) { pokemon ->
            val type = pokemon.types.first { type -> type.slot == 1 }
            val assetBackground = assetHelper.getAsset(type.name)
            Box(modifier = Modifier.height(140.dp)) {
                Card(
                    shape = RoundedCornerShape(10.dp),
                    backgroundColor = assetBackground.backgroundColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(115.dp)
                        .align(Alignment.BottomCenter)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = "#${pokemon.id}",
                            style = MaterialTheme.typography.h6,
                            color = Gray17,
                            modifier = Modifier.alpha(0.6f)
                        )
                        Text(
                            text = pokemon.name,
                            style = MaterialTheme.typography.h3,
                            color = Color.White,
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                            items(pokemon.types) { type ->
                                val assetType = assetHelper.getAsset(type.name)
                                Card(
                                    shape = RoundedCornerShape(3.dp),
                                    backgroundColor = assetType.typeColor,
                                    modifier = Modifier.height(25.dp)
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                                        modifier = Modifier.padding(horizontal = 5.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = assetType.icon),
                                            contentDescription = null,
                                            colorFilter = ColorFilter.tint(Color.White),
                                            modifier = Modifier.size(15.dp)
                                        )
                                        Text(
                                            text = type.name,
                                            style = MaterialTheme.typography.subtitle1,
                                            color = Color.White,
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_pokeball),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .size(145.dp)
                        .offset(x = 15.dp, y = 15.dp)
                        .alpha(0.3f)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_6x3),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(x = (-37).dp, y = (-18).dp)
                        .width(74.dp)
                        .height(32.dp)
                        .alpha(0.3f)
                )
                AsyncImage(
                    model = pokemon.image,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(end = 10.dp, bottom = 10.dp)
                        .size(130.dp)
                )
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
                runBlocking {
                    viewModel.getPokemon(it)
                }
            }
        },
        shape = RoundedCornerShape(10.dp),
        textStyle = MaterialTheme.typography.h5,
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
                style = MaterialTheme.typography.h5,
                color = Gray74
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp)
    )
}

