package com.montfel.pokfinder.feature.profile.ui

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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import coil.compose.AsyncImage
import com.montfel.gamerguide.core.designsystem.resources.drawableDesignSystem
import com.montfel.gamerguide.core.designsystem.resources.stringDesignSystem
import com.montfel.pokfinder.core.designsystem.components.ErrorScreen
import com.montfel.pokfinder.core.designsystem.components.LoadingScreen
import com.montfel.pokfinder.core.designsystem.components.TypeCard
import com.montfel.pokfinder.core.designsystem.model.AssetFromType
import com.montfel.pokfinder.core.designsystem.theme.fabBackground
import com.montfel.pokfinder.core.designsystem.theme.numberOverBackgroundColor
import com.montfel.pokfinder.core.designsystem.theme.primaryIcon
import com.montfel.pokfinder.core.designsystem.theme.secondaryText
import com.montfel.pokfinder.feature.profile.ui.components.About
import com.montfel.pokfinder.feature.profile.ui.components.Evolution
import com.montfel.pokfinder.feature.profile.ui.components.Stats
import com.montfel.pokfinder.feature.profile.ui.model.AboutData

@Composable
fun ProfileScreen(
    id: Int,
    onNavigateToProfile: (id: Int) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(ProfileEvent.SavePokemonId(id))
        viewModel.onEvent(ProfileEvent.FetchPokemonDetails)
        viewModel.uiEvent.collect { event ->
            when (event) {
                is ProfileUiEvent.NavigateBack -> onNavigateBack()
                is ProfileUiEvent.NavigateToProfile -> onNavigateToProfile(event.pokemonId)
            }
        }
    }

    when (uiState.stateOfUi) {
        ProfileStateOfUi.Error -> {
            ErrorScreen(onClick = { viewModel.onEvent(ProfileEvent.FetchPokemonDetails) })
        }

        ProfileStateOfUi.Loading -> {
            LoadingScreen()
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
    val deviceLanguage = Locale.current.language
    val assetFromType = AssetFromType.getAsset(uiState.profile?.types?.firstOrNull()?.name)
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    val titles = mutableListOf(stringDesignSystem.about, stringDesignSystem.stats)

    if ((uiState.evolutionChain?.size ?: 0) > 1) {
        titles.add(stringDesignSystem.evolution)
    }

    val species = uiState.species?.genera
        ?.firstOrNull { lang -> lang.language == deviceLanguage }?.name
        ?: uiState.species?.genera?.first { lang -> lang.language == "en" }?.name

    val pokedexData = mutableListOf<AboutData>()

    species?.let {
        pokedexData.add(AboutData(title = stringDesignSystem.species, description = it))
    }

    uiState.profile?.height?.let {
        pokedexData.add(AboutData(title = stringDesignSystem.height, description = "${it}m"))
    }

    uiState.profile?.weight?.let {
        pokedexData.add(AboutData(title = stringDesignSystem.weight, description = "${it}kg"))
    }

    uiState.profile?.abilitiesText?.let {
        pokedexData.add(AboutData(title = stringDesignSystem.abilities, description = it))
    }

    val training = mutableListOf<AboutData>()

    uiState.profile?.ev?.let { evPoint ->
        training.add(
            AboutData(
                title = stringDesignSystem.ev_yield,
                description = evPoint.joinToString("\n") { "${it.effort} ${it.name}" }
            )
        )
    }

    uiState.species?.captureRate?.let {
        training.add(AboutData(title = stringDesignSystem.catch_rate, description = it.toString()))
    }

    uiState.species?.baseHappiness?.let {
        training.add(AboutData(title = stringDesignSystem.base_friendship, description = it.toString()))
    }

    uiState.profile?.baseExp?.let {
        training.add(AboutData(title = stringDesignSystem.base_exp, description = it.toString()))
    }

    uiState.species?.growthRate?.let {
        training.add(AboutData(title = stringDesignSystem.growth_rate, description = it))
    }

    val breeding = mutableListOf<AboutData>()

    uiState.species?.gender?.let {
        breeding.add(AboutData(title = stringDesignSystem.gender, description = it))
    }

    uiState.species?.eggGroups?.let { groups ->
        breeding.add(
            AboutData(
                title = stringDesignSystem.egg_groups,
                description = groups.joinToString { it.name.orEmpty() }
            )
        )
    }

    uiState.species?.hatchCounter?.let {
        breeding.add(
            AboutData(
                title = stringDesignSystem.egg_cycles,
                description = "${it.cycles} (${it.steps} steps)"
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                modifier = Modifier.statusBarsPadding()
            ) {
                IconButton(onClick = { onEvent(ProfileEvent.NavigateBack) }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(id = stringDesignSystem.back),
                        tint = MaterialTheme.colors.primaryIcon
                    )
                }
            }
        },
        backgroundColor = if (uiState.stateOfUi !is ProfileStateOfUi.Success) {
            MaterialTheme.colors.fabBackground
        } else {
            assetFromType.backgroundColor
        }
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
                        painter = painterResource(id = drawableDesignSystem.ic_circle),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.primaryIcon),
                        alpha = 0.35f,
                        modifier = Modifier.size(125.dp)
                    )

                    AsyncImage(
                        model = uiState.profile?.image,
                        contentDescription = stringResource(
                            id = stringDesignSystem.pokemon_image_description,
                            uiState.profile?.name.orEmpty()
                        ),
                        modifier = Modifier.size(125.dp)
                    )
                }

                Column {
                    uiState.profile?.id?.let {
                        Text(
                            text = "#$it",
                            style = com.montfel.pokfinder.core.designsystem.theme.PokfinderTheme.typography.filterTitle,
                            color = MaterialTheme.colors.numberOverBackgroundColor
                        )
                    }

                    uiState.profile?.name?.let {
                        Text(
                            text = it,
                            style = com.montfel.pokfinder.core.designsystem.theme.PokfinderTheme.typography.applicationTitle,
                            color = MaterialTheme.colors.secondaryText,
                        )
                    }

                    uiState.profile?.types?.let { types ->
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            types.forEach { type ->
                                type.name?.let {
                                    TypeCard(typeName = it)
                                }
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
                            style = if (selectedTabIndex == index) {
                                com.montfel.pokfinder.core.designsystem.theme.PokfinderTheme.typography.filterTitle
                            } else {
                                com.montfel.pokfinder.core.designsystem.theme.PokfinderTheme.typography.description
                            },
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
                                ?.filter { lang -> lang.language == deviceLanguage }
                                ?.takeIf { list -> list.isNotEmpty() }
                                ?.random()?.flavorText
                                ?: uiState.species?.flavorTexts
                                    ?.filter { lang -> lang.language == "en" }
                                    ?.takeIf { list -> list.isNotEmpty() }
                                    ?.random()?.flavorText,
                            pokedexData = pokedexData,
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
                        )
                    }

                    2 -> {
                        uiState.evolutionChain?.let { chain ->
                            Evolution(
                                typeColor = assetFromType.typeColor,
                                evolutionChain = chain,
                                onClick = { onEvent(ProfileEvent.NavigateToProfile(it)) }
                            )
                        }
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
