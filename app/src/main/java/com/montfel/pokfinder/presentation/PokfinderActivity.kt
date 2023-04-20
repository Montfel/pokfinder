package com.montfel.pokfinder.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.play.core.review.ReviewManagerFactory
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

        showFeedbackDialog()

        setContent {
            PokfinderTheme {
                NavigationComponent()
            }
        }
    }

    private fun showFeedbackDialog() {
        val reviewManager = ReviewManagerFactory.create(this)
        reviewManager
            .requestReviewFlow()
            .addOnCompleteListener { request ->
                if (request.isSuccessful) {
                    reviewManager.launchReviewFlow(this, request.result)
                }
            }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokfinderTheme {}
}
