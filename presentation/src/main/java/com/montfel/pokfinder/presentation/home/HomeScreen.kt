package com.montfel.pokfinder.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.montfel.pokfinder.designsystem.R
import com.montfel.pokfinder.designsystem.model.AssetFromType
import com.montfel.pokfinder.designsystem.theme.fabBackground
import com.montfel.pokfinder.designsystem.theme.fabContent
import com.montfel.pokfinder.designsystem.theme.pokeballIcon
import com.montfel.pokfinder.presentation.components.ProgressIndicator
import com.montfel.pokfinder.presentation.components.RetryButton
import com.montfel.pokfinder.presentation.home.bottomsheet.BottomSheetFilter
import com.montfel.pokfinder.presentation.home.bottomsheet.GenerationBottomSheet
import com.montfel.pokfinder.presentation.home.bottomsheet.SortBottomSheet
import com.montfel.pokfinder.presentation.home.bottomsheet.filter.FilterBottomSheet
import com.montfel.pokfinder.presentation.home.components.HomeHeader
import com.montfel.pokfinder.presentation.home.components.PokemonCard
import com.montfel.pokfinder.presentation.home.components.SearchField
import com.montfel.pokfinder.presentation.home.components.TopBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    onNavigateToProfile: (id: Int) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is HomeUiEvent.NavigateToProfile -> {
                    onNavigateToProfile(event.pokemonId)
                }
            }
        }
    }

    when (uiState.stateOfUi) {
        HomeStateOfUi.Error -> {
            RetryButton(onClick = { viewModel.onEvent(HomeEvent.LoadHomePage) })
        }

        HomeStateOfUi.Loading -> {
            ProgressIndicator()
        }

        HomeStateOfUi.Success -> {
            HomeScreen(
                uiState = uiState,
                onEvent = viewModel::onEvent
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun HomeScreen(
    uiState: HomeUiState,
    onEvent: (HomeEvent) -> Unit,
) {
    var filter: BottomSheetFilter by remember { mutableStateOf(BottomSheetFilter.Generation) }
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val deviceWidth = displayMetrics.widthPixels / displayMetrics.density
    val halfWidth = deviceWidth / 2
    val lazyListState = rememberLazyListState()

    ModalBottomSheetLayout(
        sheetContent = {
            when (filter) {
                BottomSheetFilter.Generation -> GenerationBottomSheet(
                    generationList = uiState.generations,
                    generationSelected = uiState.generationSelected,
                    onGenerationSelected = { generation ->
                        onEvent(HomeEvent.FilterByGeneration(generation))
                        scope.launch(Dispatchers.Main) {
                            delay(500)
                            sheetState.hide()
                        }
                    }
                )

                BottomSheetFilter.Sort -> SortBottomSheet(
                    sortOptionSelected = uiState.sortOptionSelected,
                    onSortOptionSelected = { sortOptionSelected ->
                        onEvent(HomeEvent.SortPokemonList(sortOptionSelected))
                        scope.launch(Dispatchers.Main) {
                            delay(500)
                            sheetState.hide()
                        }
                    }
                )

                BottomSheetFilter.Filter -> FilterBottomSheet(
                    assetFromTypeList = uiState.types.map { AssetFromType.getAsset(it.name) },
                    onFilterApplied = { selectedTypes ->
                        onEvent(HomeEvent.FilterByType(selectedTypes))
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
                        text = uiState.pokemonQuery,
                        onType = { onEvent(HomeEvent.SearchPokemon(it)) },
                    )
                }

                items(
                    items = uiState.pokemons,
                    key = { it.id },
                ) { pokemon ->
                    PokemonCard(
                        pokemon = pokemon,
                        onClick = { onEvent(HomeEvent.NavigateToProfile(pokemon.id)) }
                    )
                }
            }

            val showButton by remember {
                derivedStateOf { lazyListState.firstVisibleItemIndex > 0 }
            }

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
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        uiState = HomeUiState(),
        onEvent = {}
    )
}
