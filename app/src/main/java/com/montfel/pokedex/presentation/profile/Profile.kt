package com.montfel.pokedex.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.montfel.pokedex.presentation.profile.components.About
import com.montfel.pokedex.presentation.profile.components.Stats
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
    val mainType = uiState.pokemonProfile?.types?.firstOrNull { type -> type.slot == 1 }
    val assetBackground = assetHelper.getAsset(mainType?.type?.name ?: "")
    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }
    val titles = listOf(R.string.about, R.string.stats, R.string.evolution)
    var abilities = ""
    uiState.pokemonProfile?.abilities?.forEach {
        abilities += if (it.isHidden) {
            "\n${it.ability.name} (hidden ability)"
        } else {
            "${it.slot}. ${it.ability.name}"
        }
    }
    val gender = uiState.pokemonSpecies?.genderRate?.let {
        if (it == -1) "Genderless"
        else {
            "♂ ${(8 - it.toFloat()).div(8).times(100)}% | " +
                    "♀ ${it.toFloat().div(8).times(100)}% "

        }
    } ?: "Genderless"
    val data = mapOf(
        R.string.species to "${
            uiState.pokemonSpecies?.genera
                ?.firstOrNull { lang -> lang.language == language }?.name
                ?: uiState.pokemonSpecies?.genera?.first { lang -> lang.language == "en" }?.name
        }",
        R.string.height to "${uiState.pokemonProfile?.height}m",
        R.string.weight to "${uiState.pokemonProfile?.weight}kg",
        R.string.abilities to abilities,
    )

    val training = mapOf(
        R.string.ev_yield to (uiState.pokemonProfile?.ev?.joinToString("\n") { "${it.effort} ${it.name}" }
            ?: ""),
        R.string.catch_rate to "${uiState.pokemonSpecies?.captureRate}",
        R.string.base_friendship to "${uiState.pokemonSpecies?.baseHappiness}",
        R.string.base_exp to uiState.pokemonProfile?.baseExp.toString(),
        R.string.growth_rate to "${uiState.pokemonSpecies?.growthRate}"
    )

    val breeding = mapOf(
        R.string.gender to gender,
        R.string.egg_groups to "${uiState.pokemonSpecies?.eggGroups?.joinToString { it.name }}",
        R.string.egg_cycles to "${uiState.pokemonSpecies?.hatchCounter} (${
            (uiState.pokemonSpecies?.hatchCounter?.plus(
                1
            ))?.times(255)
        } steps)",
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.getPokemonProfile(id)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = assetBackground.backgroundColor,
                elevation = 0.dp,
                modifier = Modifier.statusBarsPadding()
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
                        model = uiState.pokemonProfile?.image,
                        contentDescription = null,
                        loading = { CircularProgressIndicator(color = assetBackground.typeColor) },
                        modifier = Modifier.size(125.dp)
                    )
                }
                Column {
                    Text(
                        text = "#${uiState.pokemonProfile?.id}",
                        style = MaterialTheme.typography.filterTitle,
                        color = MaterialTheme.colors.numberOverBackgroundColor
                    )
                    Text(
                        text = uiState.pokemonProfile?.name ?: "",
                        style = MaterialTheme.typography.applicationTitle,
                        color = MaterialTheme.colors.secondaryText,
                    )
                    uiState.pokemonProfile?.types?.let { types ->
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
                            flavorText = uiState.pokemonSpecies?.flavorTexts
                                ?.filter { lang -> lang.language == language }
                                ?.takeIf { list -> list.isNotEmpty() }
                                ?.random()?.flavorText
                                ?: uiState.pokemonSpecies?.flavorTexts
                                    ?.filter { lang -> lang.language == "en" }
                                    ?.random()?.flavorText ?: "",
                            data = data,
                            training = training,
                            breeding = breeding,
                            typeColor = assetBackground.typeColor,
                            strengths = uiState.pokemonDamageRelations?.damageRelations?.doubleDamageTo
                                ?: emptyList(),
                            weaknesses = uiState.pokemonDamageRelations?.damageRelations?.doubleDamageFrom
                                ?: emptyList()
                        )
                    }
                    1 -> {
                        Stats(
                            stats = uiState.pokemonProfile?.stats ?: emptyList(),
                            typeColor = assetBackground.typeColor,
                            pokemonName = uiState.pokemonProfile?.name ?: ""
                        )
                    }
//                    2 -> {
//                        Evolution(
//                            typeColor = assetBackground.typeColor,
//                            evolutionChain = uiState.pokemonSpecies?.evolutionChain ?: emptyList()
//                        )
//                    }
                }
            }
        }
    }
}
