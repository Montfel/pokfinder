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
import coil.compose.AsyncImage
import com.montfel.pokedex.R
import com.montfel.pokedex.domain.profile.model.AboutData
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
    val mainType = uiState.profile?.types?.firstOrNull { type -> type.slot == 1 }
    val assetFromType = assetHelper.getAsset(mainType?.type?.name ?: "")
    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }
    val titles = listOf(R.string.about, R.string.stats)
    var abilities = ""
    uiState.profile?.abilities?.forEach {
        abilities += if (it.isHidden) {
            "\n${it.ability.name} (hidden ability)"
        } else {
            "${it.slot}. ${it.ability.name}"
        }
    }

    val species = uiState.species?.genera
        ?.firstOrNull { lang -> lang.language == language }?.name
        ?: uiState.species?.genera?.first { lang -> lang.language == "en" }?.name

    val data = listOf(
        AboutData(title = R.string.species, description = species ?: ""),
        AboutData(title = R.string.height, description = "${uiState.profile?.height}m"),
        AboutData(title = R.string.weight, description = "${uiState.profile?.weight}kg"),
        AboutData(title = R.string.abilities, description = abilities),
    )

    val ev = uiState.profile?.ev?.joinToString("\n") { "${it.effort} ${it.name}" } ?: ""

    val training = listOf(
        AboutData(title = R.string.ev_yield, description = ev),
        AboutData(
            title = R.string.catch_rate,
            description = uiState.species?.captureRate.toString()
        ),
        AboutData(
            title = R.string.base_friendship,
            description = uiState.species?.baseHappiness.toString()
        ),
        AboutData(title = R.string.base_exp, description = uiState.profile?.baseExp.toString()),
        AboutData(title = R.string.growth_rate, description = uiState.species?.growthRate ?: ""),
    )

    val gender = uiState.species?.genderRate?.let {
        if (it == -1) "Genderless"
        else {
            "♂ ${(8 - it.toFloat()).div(8).times(100)}% | " +
                    "♀ ${it.toFloat().div(8).times(100)}% "

        }
    } ?: "Genderless"
    val eggGroup = uiState.species?.eggGroups?.joinToString { it.name } ?: ""
    val eggCycles =
        "${uiState.species?.hatchCounter?.cycles} (${uiState.species?.hatchCounter?.steps} steps)"

    val breeding = listOf(
        AboutData(title = R.string.gender, description = gender),
        AboutData(title = R.string.egg_groups, description = eggGroup),
        AboutData(title = R.string.egg_cycles, description = eggCycles),
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.getPokemonDetails(id)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
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
        backgroundColor =
        if (uiState.isLoading) MaterialTheme.colors.fabBackground else assetFromType.backgroundColor
    ) { paddingValues ->
        if (uiState.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.primaryText,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(paddingValues)
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
                        AsyncImage(
                            model = uiState.profile?.image,
                            contentDescription = null,
                            modifier = Modifier.size(125.dp)
                        )
                    }
                    Column {
                        Text(
                            text = "#${uiState.profile?.id}",
                            style = MaterialTheme.typography.filterTitle,
                            color = MaterialTheme.colors.numberOverBackgroundColor
                        )
                        Text(
                            text = uiState.profile?.name ?: "",
                            style = MaterialTheme.typography.applicationTitle,
                            color = MaterialTheme.colors.secondaryText,
                        )
                        uiState.profile?.types?.let { types ->
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
                                flavorText = uiState.species?.flavorTexts
                                    ?.filter { lang -> lang.language == language }
                                    ?.takeIf { list -> list.isNotEmpty() }
                                    ?.random()?.flavorText
                                    ?: uiState.species?.flavorTexts
                                        ?.filter { lang -> lang.language == "en" }
                                        ?.random()?.flavorText ?: "",
                                data = data,
                                training = training,
                                breeding = breeding,
                                typeColor = assetFromType.typeColor,
                                strengths = uiState.strengths,
                                weaknesses = uiState.weaknesses,
                                immunity = uiState.immunity,
                            )
                        }
                        1 -> {
                            Stats(
                                stats = uiState.profile?.stats ?: emptyList(),
                                typeColor = assetFromType.typeColor,
                                pokemonName = uiState.profile?.name ?: ""
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
}
