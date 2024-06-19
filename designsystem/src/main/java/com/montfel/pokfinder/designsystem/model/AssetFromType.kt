package com.montfel.pokfinder.designsystem.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.montfel.pokfinder.designsystem.R

sealed class AssetFromType(
    val typeColor: Color,
    @DrawableRes val icon: Int,
    val backgroundColor: Color = Color.White
) {
    data object Bug : AssetFromType(
        typeColor = com.montfel.pokfinder.designsystem.theme.TypeBug,
        backgroundColor = com.montfel.pokfinder.designsystem.theme.BackgroundBug,
        icon = R.drawable.ic_bug
    )

    data object Dark : AssetFromType(
        typeColor = com.montfel.pokfinder.designsystem.theme.TypeDark,
        backgroundColor = com.montfel.pokfinder.designsystem.theme.BackgroundDark,
        icon = R.drawable.ic_dark
    )

    data object Dragon : AssetFromType(
        typeColor = com.montfel.pokfinder.designsystem.theme.TypeDragon,
        backgroundColor = com.montfel.pokfinder.designsystem.theme.BackgroundDragon,
        icon = R.drawable.ic_dragon
    )

    data object Electric : AssetFromType(
        typeColor = com.montfel.pokfinder.designsystem.theme.TypeElectric,
        backgroundColor = com.montfel.pokfinder.designsystem.theme.BackgroundElectric,
        icon = R.drawable.ic_electric
    )

    data object Fairy : AssetFromType(
        typeColor = com.montfel.pokfinder.designsystem.theme.TypeFairy,
        backgroundColor = com.montfel.pokfinder.designsystem.theme.BackgroundFairy,
        icon = R.drawable.ic_fairy
    )

    data object Fighting : AssetFromType(
        typeColor = com.montfel.pokfinder.designsystem.theme.TypeFighting,
        backgroundColor = com.montfel.pokfinder.designsystem.theme.BackgroundFighting,
        icon = R.drawable.ic_fighting
    )

    data object Fire : AssetFromType(
        typeColor = com.montfel.pokfinder.designsystem.theme.TypeFire,
        backgroundColor = com.montfel.pokfinder.designsystem.theme.BackgroundFire,
        icon = R.drawable.ic_fire
    )

    data object Flying : AssetFromType(
        typeColor = com.montfel.pokfinder.designsystem.theme.TypeFlying,
        backgroundColor = com.montfel.pokfinder.designsystem.theme.BackgroundFlying,
        icon = R.drawable.ic_flying
    )

    data object Ghost : AssetFromType(
        typeColor = com.montfel.pokfinder.designsystem.theme.TypeGhost,
        backgroundColor = com.montfel.pokfinder.designsystem.theme.BackgroundGhost,
        icon = R.drawable.ic_ghost
    )

    data object Grass : AssetFromType(
        typeColor = com.montfel.pokfinder.designsystem.theme.TypeGrass,
        backgroundColor = com.montfel.pokfinder.designsystem.theme.BackgroundGrass,
        icon = R.drawable.ic_grass
    )

    data object Ground : AssetFromType(
        typeColor = com.montfel.pokfinder.designsystem.theme.TypeGround,
        backgroundColor = com.montfel.pokfinder.designsystem.theme.BackgroundGround,
        icon = R.drawable.ic_ground
    )

    data object Ice : AssetFromType(
        typeColor = com.montfel.pokfinder.designsystem.theme.TypeIce,
        backgroundColor = com.montfel.pokfinder.designsystem.theme.BackgroundIce,
        icon = R.drawable.ic_ice
    )

    data object Normal : AssetFromType(
        typeColor = com.montfel.pokfinder.designsystem.theme.TypeNormal,
        backgroundColor = com.montfel.pokfinder.designsystem.theme.BackgroundNormal,
        icon = R.drawable.ic_normal
    )

    data object Poison : AssetFromType(
        typeColor = com.montfel.pokfinder.designsystem.theme.TypePoison,
        backgroundColor = com.montfel.pokfinder.designsystem.theme.BackgroundPoison,
        icon = R.drawable.ic_poison
    )

    data object Psychic : AssetFromType(
        typeColor = com.montfel.pokfinder.designsystem.theme.TypePsychic,
        backgroundColor = com.montfel.pokfinder.designsystem.theme.BackgroundPsychic,
        icon = R.drawable.ic_psychic
    )

    data object Rock : AssetFromType(
        typeColor = com.montfel.pokfinder.designsystem.theme.TypeRock,
        backgroundColor = com.montfel.pokfinder.designsystem.theme.BackgroundRock,
        icon = R.drawable.ic_rock
    )

    data object Steel : AssetFromType(
        typeColor = com.montfel.pokfinder.designsystem.theme.TypeSteel,
        backgroundColor = com.montfel.pokfinder.designsystem.theme.BackgroundSteel,
        icon = R.drawable.ic_steel
    )

    data object Water : AssetFromType(
        typeColor = com.montfel.pokfinder.designsystem.theme.TypeWater,
        backgroundColor = com.montfel.pokfinder.designsystem.theme.BackgroundWater,
        icon = R.drawable.ic_water
    )

    data object Short : AssetFromType(
        typeColor = com.montfel.pokfinder.designsystem.theme.HeightShort,
        icon = R.drawable.ic_resource_short
    )

    data object MediumHeight : AssetFromType(
        typeColor = com.montfel.pokfinder.designsystem.theme.HeightMedium,
        icon = R.drawable.ic_medium
    )

    data object Tall : AssetFromType(
        typeColor = com.montfel.pokfinder.designsystem.theme.HeightTall,
        icon = R.drawable.ic_tall
    )

    data object Light : AssetFromType(
        typeColor = com.montfel.pokfinder.designsystem.theme.WeightLight,
        icon = R.drawable.ic_light
    )

    data object NormalWeight : AssetFromType(
        typeColor = com.montfel.pokfinder.designsystem.theme.WeightNormal,
        icon = R.drawable.ic_normal
    )

    data object Heavy : AssetFromType(
        typeColor = com.montfel.pokfinder.designsystem.theme.WeightHeavy,
        icon = R.drawable.ic_heavy
    )

    companion object {
        fun getAsset(type: String): AssetFromType {
            return when (type.lowercase()) {
                "bug" -> Bug
                "dark" -> Dark
                "dragon" -> Dragon
                "electric" -> Electric
                "fairy" -> Fairy
                "fighting" -> Fighting
                "fire" -> Fire
                "flying" -> Flying
                "ghost" -> Ghost
                "grass" -> Grass
                "ground" -> Ground
                "ice" -> Ice
                "normal" -> Normal
                "poison" -> Poison
                "psychic" -> Psychic
                "rock" -> Rock
                "steel" -> Steel
                "water" -> Water
                "short" -> Short
                "medium_height" -> MediumHeight
                "tall" -> Tall
                "light" -> Light
                "normal_weight" -> NormalWeight
                "heavy" -> Heavy
                else -> Dark
            }
        }
    }
}
