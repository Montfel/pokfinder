package com.montfel.pokfinder

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PokfinderApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
