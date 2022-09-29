package com.montfel.pokfinder.domain.profile.model

import androidx.annotation.StringRes

data class AboutData(
    @StringRes val title: Int,
    val description: String
)
