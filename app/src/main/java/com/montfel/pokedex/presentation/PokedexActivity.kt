package com.montfel.pokedex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.montfel.pokedex.presentation.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokedexActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                NavigationComponent()
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
