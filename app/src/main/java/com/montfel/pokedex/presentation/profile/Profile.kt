package com.montfel.pokedex.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
    val type = uiState.pokemon?.types?.first { type -> type.slot == 1 }
    val assetBackground = assetHelper.getAsset(type?.name ?: "")
    var state by remember { mutableStateOf(0) }
    val titles = listOf(R.string.about, R.string.stats, R.string.evolution)
    val data = mapOf(
        R.string.species to "a",
        R.string.height to uiState.pokemon?.height.toString(),
        R.string.weight to uiState.pokemon?.weight.toString(),
        R.string.abilities to "b",
        R.string.weakeness to "c"
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
            verticalAlignment = Alignment.CenterVertically
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
        ) {
            Text(
                text = "Bulbasaur can be seen napping in bright sunlight. There is a seed on its " +
                        "back. By soaking up the sun's rays, the seed grows progressively larger.",
                style = MaterialTheme.typography.description,
                color = Gray74
            )
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = stringResource(id = R.string.pokedex_data),
                style = MaterialTheme.typography.filterTitle,
                color = assetBackground.typeColor
            )
            data.forEach {
                Row(
                    verticalAlignment = CenterVertically
                ) {
                    Text(
                        text = stringResource(id = it.key),
                        style = MaterialTheme.typography.pokemonType,
                        color = Gray17,
                        modifier = Modifier.width(85.dp)
                    )
                    Text(
                        text = it.value,
                        style = MaterialTheme.typography.description,
                        color = Gray74
                    )
                }
            }
        }
    }
}
