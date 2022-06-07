package com.montfel.pokedex.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.montfel.pokedex.R
import com.montfel.pokedex.domain.model.Pokemon
import com.montfel.pokedex.presentation.components.TypeCards
import com.montfel.pokedex.presentation.theme.*

@Composable
fun Profile(
    id: String,
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val assetHelper = LocalAssetHelper.current
    val type = uiState.pokemon?.types?.firstOrNull { type -> type.slot == 1 }
    val assetBackground = assetHelper.getAsset(type?.name ?: "")
    var selectedTabIndex by remember { mutableStateOf(0) }
    val titles = listOf(R.string.about, R.string.stats, R.string.evolution)
    var abilities = ""
    uiState.pokemon?.abilities?.forEach {
        abilities += if (it.isHidden) {
            "\n${it.name} (hidden ability)"
        } else {
            "${it.slot}. ${it.name}"
        }
    }
    val gender = uiState.pokemon?.genderRate?.let {
        if (it == -1) "Genderless"
        else {
            "♀ ${it.toFloat().div(8).times(100)}%, " +
                    "♂ ${(8 - it.toFloat()).div(8).times(100)}%"
        }
    } ?: "Genderless"
    val data = mapOf(
        R.string.species to "a",
        R.string.height to "${uiState.pokemon?.height}m",
        R.string.weight to "${uiState.pokemon?.weight}kg",
        R.string.abilities to abilities,
        R.string.weakeness to "c"
    )

    val training = mapOf(
        R.string.ev_yield to "a",
        R.string.catch_rate to "${uiState.pokemon?.captureRate}",
        R.string.base_friendship to "${uiState.pokemon?.baseHappiness}",
        R.string.base_exp to uiState.pokemon?.baseExp.toString(),
        R.string.growth_rate to "${uiState.pokemon?.growthRate}"
    )

    val breeding = mapOf(
        R.string.gender to gender,
        R.string.egg_groups to "${uiState.pokemon?.eggGroups?.joinToString()}",
        R.string.egg_cycles to "${uiState.pokemon?.hatchCounter} (${
            (uiState.pokemon?.hatchCounter?.plus(
                1
            ))?.times(255)
        } steps)",
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.getProfile(id)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(color = assetBackground.backgroundColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            IconButton(onClick = navController::popBackStack) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(25.dp),
            verticalAlignment = CenterVertically
        ) {
//            AsyncImage(
//                model = uiState.pokemon?.image,
//                contentDescription = null,
//                modifier = Modifier.size(125.dp)
//            )
            Image(
                painter = painterResource(id = R.drawable.ic_pokeball),
                contentDescription = null,
                modifier = Modifier.size(125.dp)
            )
            Column() {
                Text(
                    text = "#${uiState.pokemon?.id}",
                    style = MaterialTheme.typography.filterTitle,
                    color = Gray17,
                    modifier = Modifier.alpha(0.6f)
                )
                Text(
                    text = uiState.pokemon?.name ?: "",
                    style = MaterialTheme.typography.applicationTitle,
                    color = Color.White,
                )
                TypeCards(
                    pokemon = uiState.pokemon ?: Pokemon()
                )
            }
        }

        Spacer(modifier = Modifier.height(45.dp))

        TabRow(
            selectedTabIndex = selectedTabIndex,
            backgroundColor = Color.Transparent,
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    unselectedContentColor = Color.White
                ) {
                    Text(
                        text = stringResource(id = title),
                        style = if (selectedTabIndex == index) MaterialTheme.typography.filterTitle
                        else MaterialTheme.typography.description,
                        color = Color.White
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            when (selectedTabIndex) {
                0 -> {
                    About(
                        flavorText = uiState.pokemon?.flavorTexts?.random()?.flavorText ?: "",
                        data = data,
                        training = training,
                        breeding = breeding,
                        typeColor = assetBackground.typeColor
                    )
                }
                1 -> {
                    Stats(
                        stats = uiState.pokemon?.stats ?: emptyList(),
                        typeColor = assetBackground.typeColor,
                        pokemonName = uiState.pokemon?.name ?: ""
                    )
                }
                2 -> {
                    Evolution(
                        typeColor = assetBackground.typeColor,
                        evolutionChain = uiState.pokemon?.evolutionChain ?: emptyList()
                    )
                }
            }
        }
    }
}
