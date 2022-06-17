package com.montfel.pokedex.presentation.navigation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.montfel.pokedex.presentation.bottomsheet.FilterBottomSheet
import com.montfel.pokedex.presentation.home.Home
import com.montfel.pokedex.presentation.profile.Profile
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun NavigationComponent() {
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val navController = rememberNavController()
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            FilterBottomSheet()
        },
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
    ) {
        NavHost(navController = navController, startDestination = Route.Home.route) {
            composable(Route.Home.route) {
                Home(
                    navController = navController,
                    onClick = {
                        coroutineScope.launch {
                            sheetState.show()
                        }
                    }
                )
            }
            composable("profile/{id}") {
                Profile(
                    id = it.arguments?.getString("id")!!,
                    navController = navController
                )
            }
        }
    }
}
