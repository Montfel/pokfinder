package com.montfel.pokedex.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
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
    var state by remember { mutableStateOf(0) }
    val titles = listOf(R.string.about, R.string.stats, R.string.evolution)
    var abilities = ""
//    uiState.pokemon?.abilities?.forEach {
//        abilities += if (it.isHidden) {
//            "\n${it.name} (hidden ability)"
//        } else {
//            "${it.slot}. ${it.ability.name}"
//        }
//    }
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
        R.string.base_friendship to "c",
        R.string.base_exp to uiState.pokemon?.baseExp.toString(),
        R.string.growth_rate to "${uiState.pokemon?.growthRate}"
    )

    val breeding = mapOf(
        R.string.gender to gender,
        R.string.egg_groups to "${uiState.pokemon?.eggGroups?.joinToString()}",
        R.string.egg_cycles to "c",
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.getProfile(id)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(color = assetBackground.backgroundColor)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(25.dp),
            verticalAlignment = CenterVertically
        ) {
            AsyncImage(
                model = uiState.pokemon?.image,
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
        TabRow(
            selectedTabIndex = state,
            backgroundColor = Color.Transparent,
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = state == index,
                    onClick = { state = index },
                    unselectedContentColor = Color.White
                ) {
                    Text(
                        text = stringResource(id = title),
                        style = if (state == index) MaterialTheme.typography.filterTitle
                        else MaterialTheme.typography.description,
                        color = Color.White
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .background(color = Color.White)
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .padding(24.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = uiState.pokemon?.flavorTexts?.random()?.flavorText ?: "",
                style = MaterialTheme.typography.description,
                color = Gray74,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = stringResource(id = R.string.pokedex_data),
                style = MaterialTheme.typography.filterTitle,
                color = assetBackground.typeColor
            )
            Spacer(modifier = Modifier.height(20.dp))
            data.forEach {
                ProfileItem(map = it)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.training),
                style = MaterialTheme.typography.filterTitle,
                color = assetBackground.typeColor
            )
            training.forEach {
                ProfileItem(map = it)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.breeding),
                style = MaterialTheme.typography.filterTitle,
                color = assetBackground.typeColor
            )
            breeding.forEach {
                ProfileItem(map = it)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.location),
                style = MaterialTheme.typography.filterTitle,
                color = assetBackground.typeColor
            )
            breeding.forEach {
                ProfileItem(map = it)
            }
        }
    }
}

@Composable
fun ProfileItem(map: Map.Entry<Int, String>) {
    Row(
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = stringResource(id = map.key),
            style = MaterialTheme.typography.pokemonType,
            color = Gray17,
            modifier = Modifier.width(85.dp)
        )
        Text(
            text = map.value,
            style = MaterialTheme.typography.description,
            color = Gray74
        )
    }
    Spacer(modifier = Modifier.height(15.dp))
}
