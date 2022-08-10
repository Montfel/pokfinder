package com.montfel.pokedex.helper

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.montfel.pokedex.R
import com.montfel.pokedex.presentation.theme.*
import javax.inject.Singleton

sealed class AssetFromType(
    val typeColor: Color,
    val backgroundColor: Color,
    @DrawableRes val icon: Int
) {
    object Bug : AssetFromType(
        typeColor = TypeBug,
        backgroundColor = BackgroundBug,
        icon = R.drawable.ic_bug
    )

    object Dark : AssetFromType(
        typeColor = TypeDark,
        backgroundColor = BackgroundDark,
        icon = R.drawable.ic_dark
    )

    object Dragon : AssetFromType(
        typeColor = TypeDragon,
        backgroundColor = BackgroundDragon,
        icon = R.drawable.ic_dragon
    )

    object Electric : AssetFromType(
        typeColor = TypeElectric,
        backgroundColor = BackgroundElectric,
        icon = R.drawable.ic_electric
    )

    object Fairy : AssetFromType(
        typeColor = TypeFairy,
        backgroundColor = BackgroundFairy,
        icon = R.drawable.ic_fairy
    )

    object Fighting : AssetFromType(
        typeColor = TypeFighting,
        backgroundColor = BackgroundFighting,
        icon = R.drawable.ic_fighting
    )

    object Fire : AssetFromType(
        typeColor = TypeFire,
        backgroundColor = BackgroundFire,
        icon = R.drawable.ic_fire
    )

    object Flying : AssetFromType(
        typeColor = TypeFlying,
        backgroundColor = BackgroundFlying,
        icon = R.drawable.ic_flying
    )

    object Ghost : AssetFromType(
        typeColor = TypeGhost,
        backgroundColor = BackgroundGhost,
        icon = R.drawable.ic_ghost
    )

    object Grass : AssetFromType(
        typeColor = TypeGrass,
        backgroundColor = BackgroundGrass,
        icon = R.drawable.ic_grass
    )

    object Ground : AssetFromType(
        typeColor = TypeGround,
        backgroundColor = BackgroundGround,
        icon = R.drawable.ic_ground
    )

    object Ice : AssetFromType(
        typeColor = TypeIce,
        backgroundColor = BackgroundIce,
        icon = R.drawable.ic_ice
    )

    object Normal : AssetFromType(
        typeColor = TypeNormal,
        backgroundColor = BackgroundNormal,
        icon = R.drawable.ic_normal
    )

    object Poison : AssetFromType(
        typeColor = TypePoison,
        backgroundColor = BackgroundPoison,
        icon = R.drawable.ic_poison
    )

    object Psychic : AssetFromType(
        typeColor = TypePsychic,
        backgroundColor = BackgroundPsychic,
        icon = R.drawable.ic_psychic
    )

    object Rock : AssetFromType(
        typeColor = TypeRock,
        backgroundColor = BackgroundRock,
        icon = R.drawable.ic_rock
    )

    object Steel : AssetFromType(
        typeColor = TypeSteel,
        backgroundColor = BackgroundSteel,
        icon = R.drawable.ic_steel
    )

    object Water : AssetFromType(
        typeColor = TypeWater,
        backgroundColor = BackgroundWater,
        icon = R.drawable.ic_water
    )

    companion object {
        fun getAsset(type: String): AssetFromType {
            return when (type) {
                "Bug" -> Bug
                "Dark" -> Dark
                "Dragon" -> Dragon
                "Electric" -> Electric
                "Fairy" -> Fairy
                "Fighting" -> Fighting
                "Fire" -> Fire
                "Flying" -> Flying
                "Ghost" -> Ghost
                "Grass" -> Grass
                "Ground" -> Ground
                "Ice" -> Ice
                "Normal" -> Normal
                "Poison" -> Poison
                "Psychic" -> Psychic
                "Rock" -> Rock
                "Steel" -> Steel
                "Water" -> Water
                else -> Dark
            }
        }
    }
}
