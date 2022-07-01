package com.montfel.pokedex.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.montfel.pokedex.R
import com.montfel.pokedex.presentation.components.TypeCard
import com.montfel.pokedex.presentation.theme.*

@Composable
fun Profile(
    id: String,
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val language = Locale.current.language
    val uiState by viewModel.uiState.collectAsState()
    val assetHelper = LocalAssetHelper.current
    val mainType = uiState.pokemon?.types?.firstOrNull { type -> type.slot == 1 }
    val assetBackground = assetHelper.getAsset(mainType?.type?.name ?: "")
    var selectedTabIndex by remember { mutableStateOf(0) }
    val titles = listOf(R.string.about, R.string.stats, R.string.evolution)
    val strength = mainType?.type?.typeEfficacies
        ?.filter { it.damageFactor == 200 }
        ?.map { it.name }
    val weakness = mainType?.type?.typeEfficacies
        ?.filter { it.damageFactor == 0 }
        ?.map { it.name }
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
            "♂ ${(8 - it.toFloat()).div(8).times(100)}%, " +
                    "♀ ${it.toFloat().div(8).times(100)}% "

        }
    } ?: "Genderless"
    val data = mapOf(
        R.string.species to "${
            uiState.pokemon?.genera
                ?.firstOrNull { lang -> lang.language.name == language }?.name
                ?: uiState.pokemon?.genera?.first { lang -> lang.language.name == "en" }?.name
        }",
        R.string.height to "${uiState.pokemon?.height}m",
        R.string.weight to "${uiState.pokemon?.weight}kg",
        R.string.abilities to abilities,
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
        viewModel.getPokemonHeader(id)
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                IconButton(onClick = navController::popBackStack) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = null,
                        tint = MaterialTheme.colors.primaryIcon
                    )
                }
            }
        },
        backgroundColor = assetBackground.backgroundColor,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(it)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = CenterVertically
            ) {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.ic_circle),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.primaryIcon),
                        alpha = 0.35f,
                        modifier = Modifier.size(125.dp)
                    )
                    SubcomposeAsyncImage(
                        model = uiState.pokemonHeader?.image,
                        contentDescription = null,
                        loading = { CircularProgressIndicator(color = assetBackground.typeColor) },
                        modifier = Modifier.size(125.dp)
                    )
                }
                Column {
                    Text(
                        text = "#${uiState.pokemonHeader?.id}",
                        style = MaterialTheme.typography.filterTitle,
                        color = MaterialTheme.colors.primaryText.copy(alpha = 0.6f)
                    )
                    Text(
                        text = uiState.pokemonHeader?.name ?: "",
                        style = MaterialTheme.typography.applicationTitle,
                        color = MaterialTheme.colors.secondaryText,
                    )
                    uiState.pokemonHeader?.types?.let { types ->
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            types.forEach { type ->
                                TypeCard(typeName = type.type.name)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(44.dp))

            TabRow(
                selectedTabIndex = selectedTabIndex,
                backgroundColor = Color.Transparent,
            ) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                    ) {
                        Text(
                            text = stringResource(id = title),
                            style =
                            if (selectedTabIndex == index) MaterialTheme.typography.filterTitle
                            else MaterialTheme.typography.description,
                            color = MaterialTheme.colors.secondaryText
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(color = MaterialTheme.colors.surface)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                when (selectedTabIndex) {
                    0 -> {
                        About(
                            flavorText = uiState.pokemon?.flavorTexts
                                ?.filter { lang -> lang.language.name == language }
                                ?.takeIf { list -> list.isNotEmpty() }
                                ?.random()?.flavorText
                                ?: uiState.pokemon?.flavorTexts
                                    ?.filter { lang -> lang.language.name == "en" }
                                    ?.random()?.flavorText ?: "",
                            data = data,
                            training = training,
                            breeding = breeding,
                            typeColor = assetBackground.typeColor,
                            strength = strength,
                            weakness = weakness
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
}
