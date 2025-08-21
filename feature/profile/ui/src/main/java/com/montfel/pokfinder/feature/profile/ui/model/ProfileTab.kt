package com.montfel.pokfinder.feature.profile.ui.model

import androidx.annotation.StringRes
import com.montfel.pokfinder.core.designsystem.resources.stringDesignSystem

enum class ProfileTab(@param:StringRes val title: Int) {
    About(title = stringDesignSystem.about),
    Stats(title = stringDesignSystem.stats),
    Evolution(title = stringDesignSystem.evolution)
}
