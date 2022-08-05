package com.montfel.pokedex.helper

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.montfel.pokedex.R
import com.montfel.pokedex.presentation.theme.*
import javax.inject.Inject
import javax.inject.Singleton

data class AssetFromType(
    val typeColor: Color,
    val backgroundColor: Color,
    @DrawableRes val icon: Int
)

@Singleton
class AssetHelper @Inject constructor() {

    fun getAsset(type: String): AssetFromType {
        return when (type) {
            "Bug" -> {
                AssetFromType(
                    typeColor = TypeBug,
                    backgroundColor = BackgroundBug,
                    icon = R.drawable.ic_bug
                )
            }
            "Dark" -> {
                AssetFromType(
                    typeColor = TypeDark,
                    backgroundColor = BackgroundDark,
                    icon = R.drawable.ic_dark
                )
            }
            "Dragon" -> {
                AssetFromType(
                    typeColor = TypeDragon,
                    backgroundColor = BackgroundDragon,
                    icon = R.drawable.ic_dragon
                )
            }
            "Electric" -> {
                AssetFromType(
                    typeColor = TypeElectric,
                    backgroundColor = BackgroundElectric,
                    icon = R.drawable.ic_electric
                )
            }
            "Fairy" -> {
                AssetFromType(
                    typeColor = TypeFairy,
                    backgroundColor = BackgroundFairy,
                    icon = R.drawable.ic_fairy
                )
            }
            "Fighting" -> {
                AssetFromType(
                    typeColor = TypeFighting,
                    backgroundColor = BackgroundFighting,
                    icon = R.drawable.ic_fighting
                )
            }
            "Fire" -> {
                AssetFromType(
                    typeColor = TypeFire,
                    backgroundColor = BackgroundFire,
                    icon = R.drawable.ic_fire
                )
            }
            "Flying" -> {
                AssetFromType(
                    typeColor = TypeFlying,
                    backgroundColor = BackgroundFlying,
                    icon = R.drawable.ic_flying
                )
            }
            "Ghost" -> {
                AssetFromType(
                    typeColor = TypeGhost,
                    backgroundColor = BackgroundGhost,
                    icon = R.drawable.ic_ghost
                )
            }
            "Grass" -> {
                AssetFromType(
                    typeColor = TypeGrass,
                    backgroundColor = BackgroundGrass,
                    icon = R.drawable.ic_grass
                )

            }
            "Ground" -> {
                AssetFromType(
                    typeColor = TypeGround,
                    backgroundColor = BackgroundGround,
                    icon = R.drawable.ic_ground
                )
            }
            "Ice" -> {
                AssetFromType(
                    typeColor = TypeIce,
                    backgroundColor = BackgroundIce,
                    icon = R.drawable.ic_ice
                )
            }
            "Normal" -> {
                AssetFromType(
                    typeColor = TypeNormal,
                    backgroundColor = BackgroundNormal,
                    icon = R.drawable.ic_normal
                )
            }
            "Poison" -> {
                AssetFromType(
                    typeColor = TypePoison,
                    backgroundColor = BackgroundPoison,
                    icon = R.drawable.ic_poison
                )
            }
            "Psychic" -> {
                AssetFromType(
                    typeColor = TypePsychic,
                    backgroundColor = BackgroundPsychic,
                    icon = R.drawable.ic_psychic
                )
            }
            "Rock" -> {
                AssetFromType(
                    typeColor = TypeRock,
                    backgroundColor = BackgroundRock,
                    icon = R.drawable.ic_rock
                )
            }
            "Steel" -> {
                AssetFromType(
                    typeColor = TypeSteel,
                    backgroundColor = BackgroundSteel,
                    icon = R.drawable.ic_steel
                )
            }
            "Water" -> {
                AssetFromType(
                    typeColor = TypeWater,
                    backgroundColor = BackgroundWater,
                    icon = R.drawable.ic_water
                )
            }
            else -> AssetFromType(
                typeColor = TypeDark,
                backgroundColor = BackgroundDark,
                icon = R.drawable.ic_pokeball
            )
        }
    }
}
