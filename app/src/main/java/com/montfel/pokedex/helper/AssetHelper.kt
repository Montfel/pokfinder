package com.montfel.pokedex.helper

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.montfel.pokedex.R
import com.montfel.pokedex.presentation.theme.*
import javax.inject.Inject
import javax.inject.Singleton

data class Asset(
    val typeColor: Color,
    val backgroundColor: Color,
    @DrawableRes val icon: Int
)

@Singleton
class AssetHelper @Inject constructor() {

    fun getAsset(type: String): Asset {
        return when (type) {
            "Bug" -> {
                Asset(
                    typeColor = TypeBug,
                    backgroundColor = BackgroundBug,
                    icon = R.drawable.ic_bug
                )
            }
            "Dark" -> {
                Asset(
                    typeColor = TypeDark,
                    backgroundColor = BackgroundDark,
                    icon = R.drawable.ic_dark
                )
            }
            "Dragon" -> {
                Asset(
                    typeColor = TypeDragon,
                    backgroundColor = BackgroundDragon,
                    icon = R.drawable.ic_dragon
                )
            }
            "Electric" -> {
                Asset(
                    typeColor = TypeElectric,
                    backgroundColor = BackgroundElectric,
                    icon = R.drawable.ic_electric
                )
            }
            "Fairy" -> {
                Asset(
                    typeColor = TypeFairy,
                    backgroundColor = BackgroundFairy,
                    icon = R.drawable.ic_fairy
                )
            }
            "Fighting" -> {
                Asset(
                    typeColor = TypeFighting,
                    backgroundColor = BackgroundFighting,
                    icon = R.drawable.ic_fighting
                )
            }
            "Fire" -> {
                Asset(
                    typeColor = TypeFire,
                    backgroundColor = BackgroundFire,
                    icon = R.drawable.ic_fire
                )
            }
            "Flying" -> {
                Asset(
                    typeColor = TypeFlying,
                    backgroundColor = BackgroundFlying,
                    icon = R.drawable.ic_flying
                )
            }
            "Ghost" -> {
                Asset(
                    typeColor = TypeGhost,
                    backgroundColor = BackgroundGhost,
                    icon = R.drawable.ic_ghost
                )
            }
            "Grass" -> {
                Asset(
                    typeColor = TypeGrass,
                    backgroundColor = BackgroundGrass,
                    icon = R.drawable.ic_grass
                )

            }
            "Ground" -> {
                Asset(
                    typeColor = TypeGround,
                    backgroundColor = BackgroundGround,
                    icon = R.drawable.ic_ground
                )
            }
            "Ice" -> {
                Asset(
                    typeColor = TypeIce,
                    backgroundColor = BackgroundIce,
                    icon = R.drawable.ic_ice
                )
            }
            "Normal" -> {
                Asset(
                    typeColor = TypeNormal,
                    backgroundColor = BackgroundNormal,
                    icon = R.drawable.ic_normal
                )
            }
            "Poison" -> {
                Asset(
                    typeColor = TypePoison,
                    backgroundColor = BackgroundPoison,
                    icon = R.drawable.ic_poison
                )
            }
            "Psychic" -> {
                Asset(
                    typeColor = TypePsychic,
                    backgroundColor = BackgroundPsychic,
                    icon = R.drawable.ic_psychic
                )
            }
            "Rock" -> {
                Asset(
                    typeColor = TypeRock,
                    backgroundColor = BackgroundRock,
                    icon = R.drawable.ic_rock
                )
            }
            "Steel" -> {
                Asset(
                    typeColor = TypeSteel,
                    backgroundColor = BackgroundSteel,
                    icon = R.drawable.ic_steel
                )
            }
            "Water" -> {
                Asset(
                    typeColor = TypeWater,
                    backgroundColor = BackgroundWater,
                    icon = R.drawable.ic_water
                )
            }
            else -> Asset(
                typeColor = TypeDark,
                backgroundColor = BackgroundDark,
                icon = R.drawable.ic_dark
            )
        }
    }
}
