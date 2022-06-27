package com.montfel.pokedex.presentation.navigation

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.montfel.pokedex.presentation.bottomsheet.FilterBottomSheet
import com.montfel.pokedex.presentation.bottomsheet.GenerationBottomSheet
import com.montfel.pokedex.presentation.bottomsheet.SortBottomSheet
import com.montfel.pokedex.presentation.bottomsheet.SortOptions
import com.montfel.pokedex.presentation.home.Home
import com.montfel.pokedex.presentation.profile.Profile
import kotlinx.coroutines.launch

enum class BottomSheetFilter {
    Generation, Sort, Filter
}

@ExperimentalMaterialApi
@Composable
fun NavigationComponent() {
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val navController = rememberNavController()
    var filter by remember { mutableStateOf(BottomSheetFilter.Filter) }
    var sortOption by remember { mutableStateOf(SortOptions.SmallestNumber) }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            when (filter) {
                BottomSheetFilter.Generation -> GenerationBottomSheet()
                BottomSheetFilter.Sort -> SortBottomSheet {
                    sortOption = it
                    coroutineScope.launch {
                        sheetState.hide()
                    }
                }
                BottomSheetFilter.Filter -> FilterBottomSheet()
            }
        },
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
    ) {
        NavHost(
            navController = navController,
            startDestination = Route.Home.route
        ) {
            composable(Route.Home.route) {
                Home(
                    sortOption = sortOption,
                    navController = navController,
                    onClick = {
                        filter = it
                        coroutineScope.launch {
                            sheetState.show()
                        }
                    }
                )
            }
            composable(
                route = "profile/{id}",
                arguments = listOf(
                    navArgument("id") {
                        type = NavType.StringType
                    }
                )
            ) {
                Profile(
                    id = it.arguments?.getString("id") ?: "",   
                    navController = navController
                )
            }
        }
    }
}
