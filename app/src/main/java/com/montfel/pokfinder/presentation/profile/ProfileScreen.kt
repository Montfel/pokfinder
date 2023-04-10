package com.montfel.pokfinder.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.montfel.pokfinder.R
import com.montfel.pokfinder.domain.profile.model.AboutData
import com.montfel.pokfinder.presentation.components.ProgressIndicator
import com.montfel.pokfinder.presentation.components.RetryButton
import com.montfel.pokfinder.presentation.components.TypeCard
import com.montfel.pokfinder.presentation.navigation.Screen
import com.montfel.pokfinder.presentation.profile.components.About
import com.montfel.pokfinder.presentation.profile.components.Evolution
import com.montfel.pokfinder.presentation.profile.components.Stats
import com.montfel.pokfinder.presentation.theme.applicationTitle
import com.montfel.pokfinder.presentation.theme.description
import com.montfel.pokfinder.presentation.theme.fabBackground
import com.montfel.pokfinder.presentation.theme.filterTitle
import com.montfel.pokfinder.presentation.theme.numberOverBackgroundColor
import com.montfel.pokfinder.presentation.theme.primaryIcon
import com.montfel.pokfinder.presentation.theme.secondaryText

@Composable
fun Profile(
    id: String,
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                ProfileUiEvent.NavigateBack -> navController.popBackStack()
                is ProfileUiEvent.NavigateToProfile -> navController.navigate(
                    Screen.Profile.createRoute(
                        event.pokemonId
                    )
                )
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(ProfileEvent.SavePokemonId(id))
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(ProfileEvent.FetchPokemonDetails)
    }

    when (uiState.statesOfUi) {
        ProfileStateOfUi.Error -> {
            RetryButton(onClick = { viewModel.onEvent(ProfileEvent.FetchPokemonDetails) })
        }

        ProfileStateOfUi.Loading -> {
            ProgressIndicator()
        }

        ProfileStateOfUi.Success -> {
            ProfileScreen(
                uiState = uiState,
                onEvent = viewModel::onEvent
            )
        }
    }
}

@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    onEvent: (ProfileEvent) -> Unit,
) {
    val language = Locale.current.language
    val assetFromType = uiState.profile?.types?.first()?.type?.assetFromType
    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }
    val titles = mutableListOf(R.string.about, R.string.stats)

    if (uiState.evolutionChain.size > 1) {
        titles.add(R.string.evolution)
    }
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
        AboutData(title = R.string.species, description = species.orEmpty()),
        AboutData(title = R.string.height, description = "${uiState.profile?.height}m"),
        AboutData(title = R.string.weight, description = "${uiState.profile?.weight}kg"),
        AboutData(title = R.string.abilities, description = abilities),
    )

    val ev = uiState.profile?.ev?.joinToString("\n") { "${it.effort} ${it.name}" }.orEmpty()

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
        AboutData(
            title = R.string.base_exp,
            description = uiState.profile?.baseExp.toString()
        ),
        AboutData(
            title = R.string.growth_rate,
            description = uiState.species?.growthRate.orEmpty()
        ),
    )

    val gender = uiState.species?.genderRate?.let {
        if (it == -1) "Genderless"
        else {
            "♂ ${(8 - it.toFloat()).div(8).times(100)}% | " +
                    "♀ ${it.toFloat().div(8).times(100)}% "

        }
    } ?: "Genderless"

    val eggGroup = uiState.species?.eggGroups?.joinToString { it.name }.orEmpty()
    val eggCycles =
        "${uiState.species?.hatchCounter?.cycles} (${uiState.species?.hatchCounter?.steps} steps)"

    val breeding = listOf(
        AboutData(title = R.string.gender, description = gender),
        AboutData(title = R.string.egg_groups, description = eggGroup),
        AboutData(title = R.string.egg_cycles, description = eggCycles),
    )

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                modifier = Modifier.statusBarsPadding()
            ) {
                IconButton(onClick = { onEvent(ProfileEvent.NavigateBack) }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.back),
                        tint = MaterialTheme.colors.primaryIcon
                    )
                }
            }
        },
        backgroundColor =
        if (uiState.statesOfUi !is ProfileStateOfUi.Success) MaterialTheme.colors.fabBackground
        else assetFromType?.backgroundColor ?: Color.Transparent
    ) { paddingValues ->

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
                        contentDescription = stringResource(
                            id = R.string.pokemon_image_description,
                            uiState.profile?.name.orEmpty()
                        ),
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
                        text = uiState.profile?.name.orEmpty(),
                        style = MaterialTheme.typography.applicationTitle,
                        color = MaterialTheme.colors.secondaryText,
                    )
                    uiState.profile?.types?.let { types ->
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            types.forEach { type ->
                                TypeCard(type = type.type)
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
                                    ?.random()?.flavorText.orEmpty(),
                            data = data,
                            training = training,
                            breeding = breeding,
                            typeColor = assetFromType?.typeColor ?: Color.Transparent,
                            strengths = uiState.strengths,
                            weaknesses = uiState.weaknesses,
                            immunity = uiState.immunity,
                        )
                    }

                    1 -> {
                        Stats(
                            stats = uiState.profile?.stats ?: emptyList(),
                            typeColor = assetFromType?.typeColor ?: Color.Transparent,
                            pokemonName = uiState.profile?.name.orEmpty()
                        )
                    }

                    2 -> {
                        Evolution(
                            typeColor = assetFromType?.typeColor ?: Color.Transparent,
                            evolutionChain = uiState.evolutionChain,
                            onClick = { onEvent(ProfileEvent.NavigateToProfile(it)) }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(
        uiState = ProfileUiState(),
        onEvent = {}
    )
}
