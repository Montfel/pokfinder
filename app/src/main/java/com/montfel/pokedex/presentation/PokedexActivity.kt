package com.montfel.pokedex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.tooling.preview.Preview
import com.montfel.pokedex.AssetHelper
import com.montfel.pokedex.presentation.theme.LocalAssetHelper
import com.montfel.pokedex.presentation.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokedexActivity : ComponentActivity() {

    @Inject
    lateinit var assetHelper: AssetHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(
                LocalAssetHelper provides assetHelper
            ) {
                PokedexTheme {
                    NavigationComponent()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokedexTheme {

    }
}
