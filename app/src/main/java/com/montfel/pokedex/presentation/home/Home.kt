package com.montfel.pokedex.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.montfel.pokedex.R
import com.montfel.pokedex.presentation.components.RetryButton
import com.montfel.pokedex.presentation.home.bottomsheet.GenerationBottomSheet
import com.montfel.pokedex.presentation.home.bottomsheet.SortBottomSheet
import com.montfel.pokedex.presentation.home.components.HomeHeader
import com.montfel.pokedex.presentation.home.components.PokemonCard
import com.montfel.pokedex.presentation.home.components.SearchField
import com.montfel.pokedex.presentation.home.components.TopBar
import com.montfel.pokedex.presentation.navigation.Screen
import com.montfel.pokedex.presentation.theme.fabBackground
import com.montfel.pokedex.presentation.theme.fabContent
import com.montfel.pokedex.presentation.theme.pokeballIcon
import com.montfel.pokedex.presentation.theme.primaryInput
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class BottomSheetFilter {
    Generation, Sort
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Home(
    navController: NavController,
    deviceWidth: Float,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    var filter by rememberSaveable { mutableStateOf(BottomSheetFilter.Generation) }
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val halfWidth = deviceWidth / 2
    val lazyListState = rememberLazyListState()

    ModalBottomSheetLayout(
        sheetContent = {
            when (filter) {
                BottomSheetFilter.Generation -> GenerationBottomSheet(
                    generationList = uiState.generationList,
                    generationSelected = uiState.generationSelected,
                    onGenerationSelected = { generation ->
                        viewModel.filterByGeneration(generation)
                        scope.launch(Dispatchers.Main) {
                            delay(500)
                            sheetState.hide()
                        }
                    }
                )
                BottomSheetFilter.Sort -> SortBottomSheet(
                    sortOptionSelected = uiState.sortOptionSelected,
                    onSortOptionSelected = { sortOptionSelected ->
                        viewModel.sortPokemons(sortOptionSelected)
                        scope.launch(Dispatchers.Main) {
                            delay(500)
                            sheetState.hide()
                        }
                    }
                )
            }
        },
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_pokeball),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colors.pokeballIcon),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .align(Alignment.TopCenter)
                    .offset(y = (-halfWidth).dp)
            )
            LazyColumn(
                state = lazyListState,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                item {
                    TopBar(
                        onClick = {
                            filter = it
                            scope.launch(Dispatchers.Main) {
                                sheetState.show()
                            }
                        }
                    )

                    HomeHeader()

                    SearchField(
                        text = uiState.query,
                        onType = viewModel::searchPokemon,
                    )
                }

                items(
                    items = uiState.pokemonList,
                    key = { it.id },
                ) { pokemon ->
                    PokemonCard(pokemon = pokemon) {
                        navController.navigate(Screen.Profile.createRoute(pokemon.id))
                    }
                }
            }

            val showButton by remember { derivedStateOf { lazyListState.firstVisibleItemIndex > 0 } }

            if (showButton) {
                FloatingActionButton(
                    onClick = {
                        scope.launch {
                            lazyListState.scrollToItem(0)
                        }
                    },
                    backgroundColor = MaterialTheme.colors.fabBackground,
                    contentColor = MaterialTheme.colors.fabContent,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = stringResource(id = R.string.button_scroll_top_page)
                    )
                }
            }

            if (uiState.isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.primaryInput,
                    modifier = Modifier.align(Alignment.Center)
                )
            } else if (uiState.hasError) {
                RetryButton(
                    onClick = viewModel::loadHomePage,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
