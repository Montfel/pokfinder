package com.montfel.pokedex.domain.profile.model

import androidx.annotation.StringRes

data class AboutData(
    @StringRes val title: Int,
    val description: String
)
