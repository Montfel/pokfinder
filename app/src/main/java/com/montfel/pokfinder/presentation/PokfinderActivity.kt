package com.montfel.pokfinder.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.window.layout.WindowMetricsCalculator
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.montfel.pokfinder.presentation.navigation.NavigationComponent
import com.montfel.pokfinder.presentation.theme.PokfinderTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class PokfinderActivity : ComponentActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAnalytics = Firebase.analytics
        setContent {
            PokfinderTheme {
                NavigationComponent(
                    getWidth()
                )
            }
        }
    }

    private fun getWidth(): Float {
        val metrics = WindowMetricsCalculator.getOrCreate()
            .computeCurrentWindowMetrics(this)

        return (metrics.bounds.width() / resources.displayMetrics.density)
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokfinderTheme {}
}
