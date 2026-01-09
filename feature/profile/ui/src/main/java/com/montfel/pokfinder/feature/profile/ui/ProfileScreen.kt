package com.montfel.pokfinder.feature.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import coil3.compose.AsyncImage
import com.montfel.pokfinder.core.designsystem.R
import com.montfel.pokfinder.core.designsystem.components.TypeCard
import com.montfel.pokfinder.core.designsystem.model.AssetFromType
import com.montfel.pokfinder.core.designsystem.resources.drawableDesignSystem
import com.montfel.pokfinder.core.designsystem.resources.stringDesignSystem
import com.montfel.pokfinder.core.designsystem.theme.PokfinderTheme
import com.montfel.pokfinder.feature.profile.ui.components.About
import com.montfel.pokfinder.feature.profile.ui.components.Evolution
import com.montfel.pokfinder.feature.profile.ui.components.Stats
import com.montfel.pokfinder.feature.profile.ui.model.AboutData
import com.montfel.pokfinder.feature.profile.ui.model.ProfileTab

@Composable
internal fun ProfileScreen(
    uiState: ProfileUiState,
    onEvent: (ProfileEvent) -> Unit,
) {
    val deviceLanguage = Locale.current.language
    val assetFromType = AssetFromType.getAsset(uiState.profile?.types?.firstOrNull()?.name)
    var selectedTab by rememberSaveable { mutableStateOf(ProfileTab.About) }
    val tabs = if ((uiState.evolutionChain?.size ?: 0) > 1) {
        ProfileTab.entries
    } else {
        ProfileTab.entries.dropLast(1)
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
        training.add(
            AboutData(
                title = stringDesignSystem.base_friendship,
                description = it.toString()
            )
        )
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
            Row(
                modifier = Modifier.statusBarsPadding()
            ) {
                IconButton(onClick = { onEvent(ProfileEvent.NavigateBack) }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_back),
                        contentDescription = stringResource(id = stringDesignSystem.back),
                        tint = PokfinderTheme.palette.primaryIcon
                    )
                }
            }
        },
        containerColor = if (uiState.stateOfUi !is ProfileStateOfUi.Success) {
            PokfinderTheme.palette.fabBackground
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
                        colorFilter = ColorFilter.tint(PokfinderTheme.palette.primaryIcon),
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
                            style = PokfinderTheme.typography.filterTitle,
                            color = PokfinderTheme.palette.numberOverBackgroundColor
                        )
                    }

                    uiState.profile?.name?.let {
                        Text(
                            text = it,
                            style = PokfinderTheme.typography.applicationTitle,
                            color = PokfinderTheme.palette.secondaryText,
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
                selectedTabIndex = selectedTab.ordinal,
                containerColor = Color.Transparent,
                divider = {},
                indicator = { tabPositions ->
                    val rightOffset =
                        (tabPositions[selectedTab.ordinal].width - 100.dp).div(2.dp).dp

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .tabIndicatorOffset(tabPositions[selectedTab.ordinal]),
                    ) {
                        Image(
                            painter = painterResource(id = drawableDesignSystem.ic_pokeball),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Color.White),
                            alpha = 0.2f,
                            modifier = Modifier
                                .requiredSize(100.dp)
                                .offset(y = 25.dp, x = rightOffset)
                        )
                    }
                },
            ) {
                tabs.forEach { tab ->
                    Tab(
                        selected = selectedTab == tab,
                        onClick = { selectedTab = ProfileTab.entries[tab.ordinal] },
                        modifier = Modifier.height(50.dp)
                    ) {
                        Text(
                            text = stringResource(id = tab.title),
                            style = if (selectedTab == tab) {
                                PokfinderTheme.typography.filterTitle
                            } else {
                                PokfinderTheme.typography.description
                            },
                            color = PokfinderTheme.palette.secondaryText
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(color = PokfinderTheme.palette.surface)
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                when (selectedTab) {
                    ProfileTab.About -> {
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

                    ProfileTab.Stats -> {
                        Stats(
                            stats = uiState.profile?.stats ?: emptyList(),
                            typeColor = assetFromType.typeColor,
                        )
                    }

                    ProfileTab.Evolution -> {
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
internal fun ProfileScreenPreview() {
    ProfileScreen(
        uiState = ProfileUiState(),
        onEvent = {}
    )
}
