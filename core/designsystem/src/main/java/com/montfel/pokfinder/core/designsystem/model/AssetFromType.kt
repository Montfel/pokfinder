package com.montfel.pokfinder.core.designsystem.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.montfel.pokfinder.core.designsystem.R
import com.montfel.pokfinder.core.designsystem.theme.BackgroundBug
import com.montfel.pokfinder.core.designsystem.theme.BackgroundDark
import com.montfel.pokfinder.core.designsystem.theme.BackgroundDragon
import com.montfel.pokfinder.core.designsystem.theme.BackgroundElectric
import com.montfel.pokfinder.core.designsystem.theme.BackgroundFairy
import com.montfel.pokfinder.core.designsystem.theme.BackgroundFighting
import com.montfel.pokfinder.core.designsystem.theme.BackgroundFire
import com.montfel.pokfinder.core.designsystem.theme.BackgroundFlying
import com.montfel.pokfinder.core.designsystem.theme.BackgroundGhost
import com.montfel.pokfinder.core.designsystem.theme.BackgroundGrass
import com.montfel.pokfinder.core.designsystem.theme.BackgroundGround
import com.montfel.pokfinder.core.designsystem.theme.BackgroundIce
import com.montfel.pokfinder.core.designsystem.theme.BackgroundNormal
import com.montfel.pokfinder.core.designsystem.theme.BackgroundPoison
import com.montfel.pokfinder.core.designsystem.theme.BackgroundPsychic
import com.montfel.pokfinder.core.designsystem.theme.BackgroundRock
import com.montfel.pokfinder.core.designsystem.theme.BackgroundSteel
import com.montfel.pokfinder.core.designsystem.theme.BackgroundWater
import com.montfel.pokfinder.core.designsystem.theme.HeightMedium
import com.montfel.pokfinder.core.designsystem.theme.HeightShort
import com.montfel.pokfinder.core.designsystem.theme.HeightTall
import com.montfel.pokfinder.core.designsystem.theme.TypeBug
import com.montfel.pokfinder.core.designsystem.theme.TypeDark
import com.montfel.pokfinder.core.designsystem.theme.TypeDragon
import com.montfel.pokfinder.core.designsystem.theme.TypeElectric
import com.montfel.pokfinder.core.designsystem.theme.TypeFairy
import com.montfel.pokfinder.core.designsystem.theme.TypeFighting
import com.montfel.pokfinder.core.designsystem.theme.TypeFire
import com.montfel.pokfinder.core.designsystem.theme.TypeFlying
import com.montfel.pokfinder.core.designsystem.theme.TypeGhost
import com.montfel.pokfinder.core.designsystem.theme.TypeGrass
import com.montfel.pokfinder.core.designsystem.theme.TypeGround
import com.montfel.pokfinder.core.designsystem.theme.TypeIce
import com.montfel.pokfinder.core.designsystem.theme.TypeNormal
import com.montfel.pokfinder.core.designsystem.theme.TypePoison
import com.montfel.pokfinder.core.designsystem.theme.TypePsychic
import com.montfel.pokfinder.core.designsystem.theme.TypeRock
import com.montfel.pokfinder.core.designsystem.theme.TypeSteel
import com.montfel.pokfinder.core.designsystem.theme.TypeWater
import com.montfel.pokfinder.core.designsystem.theme.WeightHeavy
import com.montfel.pokfinder.core.designsystem.theme.WeightLight
import com.montfel.pokfinder.core.designsystem.theme.WeightNormal

sealed class AssetFromType(
    val typeColor: Color,
    @param:DrawableRes val icon: Int,
    val backgroundColor: Color = Color.White
) {
    data object Bug : AssetFromType(
        typeColor = TypeBug,
        backgroundColor = BackgroundBug,
        icon = R.drawable.ic_bug
    )

    data object Dark : AssetFromType(
        typeColor = TypeDark,
        backgroundColor = BackgroundDark,
        icon = R.drawable.ic_dark
    )

    data object Dragon : AssetFromType(
        typeColor = TypeDragon,
        backgroundColor = BackgroundDragon,
        icon = R.drawable.ic_dragon
    )

    data object Electric : AssetFromType(
        typeColor = TypeElectric,
        backgroundColor = BackgroundElectric,
        icon = R.drawable.ic_electric
    )

    data object Fairy : AssetFromType(
        typeColor = TypeFairy,
        backgroundColor = BackgroundFairy,
        icon = R.drawable.ic_fairy
    )

    data object Fighting : AssetFromType(
        typeColor = TypeFighting,
        backgroundColor = BackgroundFighting,
        icon = R.drawable.ic_fighting
    )

    data object Fire : AssetFromType(
        typeColor = TypeFire,
        backgroundColor = BackgroundFire,
        icon = R.drawable.ic_fire
    )

    data object Flying : AssetFromType(
        typeColor = TypeFlying,
        backgroundColor = BackgroundFlying,
        icon = R.drawable.ic_flying
    )

    data object Ghost : AssetFromType(
        typeColor = TypeGhost,
        backgroundColor = BackgroundGhost,
        icon = R.drawable.ic_ghost
    )

    data object Grass : AssetFromType(
        typeColor = TypeGrass,
        backgroundColor = BackgroundGrass,
        icon = R.drawable.ic_grass
    )

    data object Ground : AssetFromType(
        typeColor = TypeGround,
        backgroundColor = BackgroundGround,
        icon = R.drawable.ic_ground
    )

    data object Ice : AssetFromType(
        typeColor = TypeIce,
        backgroundColor = BackgroundIce,
        icon = R.drawable.ic_ice
    )

    data object Normal : AssetFromType(
        typeColor = TypeNormal,
        backgroundColor = BackgroundNormal,
        icon = R.drawable.ic_normal
    )

    data object Poison : AssetFromType(
        typeColor = TypePoison,
        backgroundColor = BackgroundPoison,
        icon = R.drawable.ic_poison
    )

    data object Psychic : AssetFromType(
        typeColor = TypePsychic,
        backgroundColor = BackgroundPsychic,
        icon = R.drawable.ic_psychic
    )

    data object Rock : AssetFromType(
        typeColor = TypeRock,
        backgroundColor = BackgroundRock,
        icon = R.drawable.ic_rock
    )

    data object Steel : AssetFromType(
        typeColor = TypeSteel,
        backgroundColor = BackgroundSteel,
        icon = R.drawable.ic_steel
    )

    data object Water : AssetFromType(
        typeColor = TypeWater,
        backgroundColor = BackgroundWater,
        icon = R.drawable.ic_water
    )

    data object Short : AssetFromType(
        typeColor = HeightShort,
        icon = R.drawable.ic_resource_short
    )

    data object MediumHeight : AssetFromType(
        typeColor = HeightMedium,
        icon = R.drawable.ic_medium
    )

    data object Tall : AssetFromType(
        typeColor = HeightTall,
        icon = R.drawable.ic_tall
    )

    data object Light : AssetFromType(
        typeColor = WeightLight,
        icon = R.drawable.ic_light
    )

    data object NormalWeight : AssetFromType(
        typeColor = WeightNormal,
        icon = R.drawable.ic_normal
    )

    data object Heavy : AssetFromType(
        typeColor = WeightHeavy,
        icon = R.drawable.ic_heavy
    )

    companion object {
        fun getAsset(type: String?): AssetFromType {
            return when (type?.lowercase()) {
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
