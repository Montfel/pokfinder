package com.montfel.pokedex.presentation.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val Colors.primaryText: Color
    get() = if (isLight) Gray17 else Color.White.copy(alpha = 0.87f)

val Colors.primaryVariantText: Color
    get() = if (isLight) Gray74 else Color.White.copy(alpha = 0.6f)

val Colors.secondaryText: Color
    get() = if (isLight) Color.White else Color.White

val Colors.primaryInput: Color
    get() = if (isLight) TypePsychic else TypePsychic

val Colors.secondaryInput: Color
    get() = if (isLight) GrayF2 else GrayF2

val Colors.secondaryVariantInput: Color
    get() = if (isLight) GrayE2 else GrayE2

val Colors.primaryIcon: Color
    get() = if (isLight) Color.White else Color.White

val Colors.onTypeColor: Color
    get() = Color.White

// Text
internal val Gray17 = Color(0xFF17171B)
internal val Gray74 = Color(0xFF747476)
internal val GrayE2 = Color(0xFFE2E2E2)
internal val GrayE5 = Color(0xFFE5E5E5)
internal val GrayEC = Color(0xFFECECEC)
internal val GrayF2 = Color(0xFFF2F2F2)
val GrayF5 = Color(0xFFF5F5F5)

// Height
val HeightShort = Color(0xFFFFC5E6)
val HeightMedium = Color(0xFFAEBFD7)
val HeightTall = Color(0xFFAAACB8)

// Weight
val WeightLight = Color(0xFF99CD7C)
val WeightNormal = Color(0xFF57B2DC)
val WeightHeavy = Color(0xFF5A92A5)

// Type
val TypeBug = Color(0xFF8CB230)
val TypeDark = Color(0xFF58575F)
val TypeDragon = Color(0xFF0F6AC0)
val TypeElectric = Color(0xFFEED535)
val TypeFairy = Color(0xFFED6EC7)
val TypeFighting = Color(0xFFD04164)
val TypeFire = Color(0xFFFD7D24)
val TypeFlying = Color(0xFF748FC9)
val TypeGhost = Color(0xFF556AAE)
val TypeGrass = Color(0xFF62B957)
val TypeGround = Color(0xFFDD7748)
val TypeIce = Color(0xFF61CEC0)
val TypeNormal = Color(0xFF9DA0AA)
val TypePoison = Color(0xFFA552CC)
val TypePsychic = Color(0xFFEA5D60)
val TypeRock = Color(0xFFBAAB82)
val TypeSteel = Color(0xFF417D9A)
val TypeWater = Color(0xFF4A90DA)

// Background
val BackgroundBug = Color(0xFF8BD674)
val BackgroundDark = Color(0xFF6F6E78)
val BackgroundDragon = Color(0xFF7383B9)
val BackgroundElectric = Color(0xFFF2CB55)
val BackgroundFairy = Color(0xFFEBA8C3)
val BackgroundFighting = Color(0xFFEB4971)
val BackgroundFire = Color(0xFFFFA756)
val BackgroundFlying = Color(0xFF83A2E3)
val BackgroundGhost = Color(0xFF8571BE)
val BackgroundGrass = Color(0xFF8BBE8A)
val BackgroundGround = Color(0xFFF78551)
val BackgroundIce = Color(0xFF91D8DF)
val BackgroundNormal = Color(0xFFB5B9C4)
val BackgroundPoison = Color(0xFF9F6E97)
val BackgroundPsychic = Color(0xFFFF6568)
val BackgroundRock = Color(0xFFD4C294)
val BackgroundSteel = Color(0xFF4C91B2)
val BackgroundWater = Color(0xFF58ABF6)
