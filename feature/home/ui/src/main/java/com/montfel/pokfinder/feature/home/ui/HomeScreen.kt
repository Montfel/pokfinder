package com.montfel.pokfinder.feature.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.montfel.pokfinder.core.designsystem.R
import com.montfel.pokfinder.core.designsystem.components.ErrorScreen
import com.montfel.pokfinder.core.designsystem.components.LoadingScreen
import com.montfel.pokfinder.core.designsystem.theme.PokfinderTheme
import com.montfel.pokfinder.feature.home.domain.model.PokemonHome
import com.montfel.pokfinder.feature.home.ui.bottomsheet.BottomSheetType
import com.montfel.pokfinder.feature.home.ui.bottomsheet.filter.FilterBottomSheet
import com.montfel.pokfinder.feature.home.ui.bottomsheet.generation.GenerationBottomSheet
import com.montfel.pokfinder.feature.home.ui.bottomsheet.sort.SortBottomSheet
import com.montfel.pokfinder.feature.home.ui.components.HomeHeader
import com.montfel.pokfinder.feature.home.ui.components.PokemonCard
import com.montfel.pokfinder.feature.home.ui.components.SearchField
import com.montfel.pokfinder.feature.home.ui.components.TopBar
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    uiState: HomeUiState,
    pokemonName: String,
    pokemonLazyPagingItems: LazyPagingItems<PokemonHome>,
    onEvent: (HomeEvent) -> Unit,
) {
    var bottomSheetType: BottomSheetType by remember { mutableStateOf(BottomSheetType.Generation) }
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current
    val displayMetrics = context.resources.displayMetrics
    val deviceWidth = displayMetrics.widthPixels / displayMetrics.density
    val halfWidth = deviceWidth / 2
    val lazyListState = rememberLazyListState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PokfinderTheme.palette.background)
            .navigationBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_pokeball),
            contentDescription = null,
            colorFilter = ColorFilter.tint(PokfinderTheme.palette.pokeballIcon),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .align(Alignment.TopCenter)
                .offset(y = (-halfWidth).dp)
        )

        LazyColumn(
            state = lazyListState,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .statusBarsPadding()
        ) {
            item {
                TopBar(
                    hasGenerations = uiState.generations.isNotEmpty(),
                    hasTypes = uiState.types.isNotEmpty(),
                    onClick = {
                        bottomSheetType = it
                        isSheetOpen = true
                    }
                )

                HomeHeader()

                SearchField(
                    text = pokemonName,
                    onType = { onEvent(HomeEvent.SearchPokemon(it)) },
                )
            }

            items(
                count = pokemonLazyPagingItems.itemCount,
                key = pokemonLazyPagingItems.itemKey(PokemonHome::id),
            ) { index ->
                val pokemon = pokemonLazyPagingItems[index]

                pokemon?.let {
                    PokemonCard(
                        pokemon = it,
                        onClick = { onEvent(HomeEvent.NavigateToProfile(it.id)) }
                    )
                }
            }

            item {
                when (pokemonLazyPagingItems.loadState.refresh) {
                    is LoadState.Error -> {
                        ErrorScreen(onClick = pokemonLazyPagingItems::refresh)
                    }

                    is LoadState.Loading -> {
                        LoadingScreen()
                    }

                    is LoadState.NotLoading -> {}
                }
            }

            item {
                when (pokemonLazyPagingItems.loadState.append) {
                    is LoadState.Error -> {
                        ErrorScreen(onClick = pokemonLazyPagingItems::retry)
                    }

                    is LoadState.Loading -> {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            CircularProgressIndicator(color = PokfinderTheme.palette.primaryInput)
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
                containerColor = PokfinderTheme.palette.fabBackground,
                contentColor = PokfinderTheme.palette.fabContent,
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

    if (isSheetOpen) {
        ModalBottomSheet(
            containerColor = PokfinderTheme.palette.surface,
            sheetState = sheetState,
            onDismissRequest = {
                isSheetOpen = false
            },
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        ) {
            when (bottomSheetType) {
                BottomSheetType.Generation -> {
                    GenerationBottomSheet(
                        generationList = uiState.generations,
                        generationSelected = uiState.generationSelected,
                        onGenerationSelected = { generation ->
                            onEvent(HomeEvent.FilterByGeneration(generation))
                            isSheetOpen = false
                        }
                    )
                }

                BottomSheetType.Sort -> {
                    SortBottomSheet(
                        sortOptionSelected = uiState.sortOptionSelected,
                        onSortOptionSelected = { sortOptionSelected ->
                            onEvent(HomeEvent.SortPokemonList(sortOptionSelected))
                            isSheetOpen = false
                        }
                    )
                }

                BottomSheetType.Filter -> {
                    FilterBottomSheet(
                        types = uiState.types,
                        onFilterApplied = { selectedTypes ->
                            onEvent(HomeEvent.FilterByTypes(selectedTypes))
                            isSheetOpen = false
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        uiState = HomeUiState(),
        pokemonName = "Lacey Dejesus",
        pokemonLazyPagingItems = flowOf(
            PagingData.from(
                listOf(
                    PokemonHome(
                        id = 8484,
                        name = "Jame Slater",
                        types = listOf()
                    )
                )
            )
        ).collectAsLazyPagingItems(),
        onEvent = {}
    )
}
