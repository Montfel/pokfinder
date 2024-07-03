package com.montfel.pokfinder.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.montfel.pokfinder.core.designsystem.R
import com.montfel.pokfinder.core.designsystem.components.ErrorScreen
import com.montfel.pokfinder.core.designsystem.components.LoadingScreen
import com.montfel.pokfinder.core.designsystem.theme.fabBackground
import com.montfel.pokfinder.core.designsystem.theme.fabContent
import com.montfel.pokfinder.core.designsystem.theme.pokeballIcon
import com.montfel.pokfinder.core.designsystem.theme.primaryInput
import com.montfel.pokfinder.domain.home.model.PokemonHome
import com.montfel.pokfinder.presentation.home.bottomsheet.BottomSheetType
import com.montfel.pokfinder.presentation.home.bottomsheet.generation.GenerationBottomSheet
import com.montfel.pokfinder.presentation.home.bottomsheet.sort.SortBottomSheet
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
    val pokemonsLazyPagingItems = uiState.pokemonsPagingDataFlow.collectAsLazyPagingItems()

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is HomeUiEvent.NavigateToProfile -> onNavigateToProfile(event.pokemonId)
            }
        }
    }

    HomeScreen(
        uiState = uiState,
        pokemonsLazyPagingItems = pokemonsLazyPagingItems,
        onEvent = viewModel::onEvent
    )
}

@ExperimentalMaterialApi
@Composable
private fun HomeScreen(
    uiState: HomeUiState,
    pokemonsLazyPagingItems: LazyPagingItems<PokemonHome>,
    onEvent: (HomeEvent) -> Unit,
) {
    var bottomSheetType: BottomSheetType by remember { mutableStateOf(BottomSheetType.Generation) }
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
            when (bottomSheetType) {
                BottomSheetType.Generation -> {
                    GenerationBottomSheet(
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
                }

                BottomSheetType.Sort -> {
                    SortBottomSheet(
                        sortOptionSelected = uiState.sortOptionSelected,
                        onSortOptionSelected = { sortOptionSelected ->
                            onEvent(HomeEvent.SortPokemonList(sortOptionSelected))
                            scope.launch(Dispatchers.Main) {
                                delay(500)
                                sheetState.hide()
                            }
                        }
                    )
                }

                BottomSheetType.Filter -> {
                    FilterBottomSheet(
                        types = uiState.types,
                        onFilterApplied = { selectedTypes ->
                            onEvent(HomeEvent.FilterByTypes(selectedTypes))
                            scope.launch(Dispatchers.Main) {
                                delay(500)
                                sheetState.hide()
                            }
                        }
                    )
                }
            }
        },
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
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
                            bottomSheetType = it
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
                    count = pokemonsLazyPagingItems.itemCount,
                    key = pokemonsLazyPagingItems.itemKey(PokemonHome::id),
                ) { index ->
                    val pokemon = pokemonsLazyPagingItems[index]

                    pokemon?.let {
                        PokemonCard(
                            pokemon = it,
                            onClick = { onEvent(HomeEvent.NavigateToProfile(it.id)) }
                        )
                    }
                }

                item {
                    when (pokemonsLazyPagingItems.loadState.refresh) {
                        is LoadState.Error -> {
                            ErrorScreen(onClick = pokemonsLazyPagingItems::refresh)
                        }

                        is LoadState.Loading -> {
                            LoadingScreen()
                        }

                        is LoadState.NotLoading -> {}
                    }
                }

                item {
                    when (pokemonsLazyPagingItems.loadState.append) {
                        is LoadState.Error -> {
                            ErrorScreen(onClick = pokemonsLazyPagingItems::retry)
                        }

                        is LoadState.Loading -> {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                CircularProgressIndicator(color = MaterialTheme.colors.primaryInput)
                            }
                        }

                        is LoadState.NotLoading -> {}
                    }
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
