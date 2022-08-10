package com.montfel.pokedex.presentation.home.bottomsheet
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.verticalScroll
//import androidx.compose.material.Button
//import androidx.compose.material.ButtonDefaults
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.unit.dp
//import com.montfel.pokedex.R
//import com.montfel.pokedex.domain.AssetFromType
//import com.montfel.pokedex.presentation.home.bottomsheet.components.BottomSheetHeader
//import com.montfel.pokedex.presentation.home.bottomsheet.components.FilterSection
//import com.montfel.pokedex.presentation.theme.*
//
//@Composable
//fun FilterBottomSheet(
//    assetFromTypeList: List<AssetFromType>,
//    onFilterApplied: () -> Unit
//) {
//    val typesSelected = remember { mutableStateListOf<Int>() }
//    val weaknessesSelected = remember { mutableStateListOf<Int>() }
//    val heightsSelected = remember { mutableStateListOf<Int>() }
//    val weightsSelected = remember { mutableStateListOf<Int>() }
//
//    val heightList = listOf(
//        AssetFromType(
//            typeColor = HeightShort,
//            icon = R.drawable.ic_resource_short,
//            backgroundColor = Color.White
//        ),
//        AssetFromType(
//            typeColor = HeightMedium,
//            icon = R.drawable.ic_medium,
//            backgroundColor = Color.White
//        ),
//        AssetFromType(
//            typeColor = HeightTall,
//            icon = R.drawable.ic_tall,
//            backgroundColor = Color.White
//        )
//    )
//
//    val weightList = listOf(
//        AssetFromType(
//            typeColor = WeightLight,
//            icon = R.drawable.ic_light,
//            backgroundColor = Color.White
//        ),
//        AssetFromType(
//            typeColor = WeightNormal,
//            icon = R.drawable.ic_normal,
//            backgroundColor = Color.White
//        ),
//        AssetFromType(
//            typeColor = WeightHeavy,
//            icon = R.drawable.ic_heavy,
//            backgroundColor = Color.White
//        )
//    )
//
//    Column(
//        verticalArrangement = Arrangement.spacedBy(32.dp),
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = 30.dp)
//            .verticalScroll(rememberScrollState())
//    ) {
//        BottomSheetHeader(
//            title = R.string.filters,
//            description = R.string.filters_description,
//            Modifier.padding(horizontal = 16.dp)
//        )
//
//        FilterSection(
//            title = R.string.types,
//            items = assetFromTypeList,
//            itemsSelected = typesSelected,
//            onFilterSelected = {
//                if (typesSelected.contains(it)) {
//                    typesSelected.remove(it)
//                } else {
//                    typesSelected.add(it)
//                }
//            }
//        )
//
//        FilterSection(
//            title = R.string.weakeness,
//            items = assetFromTypeList,
//            itemsSelected = weaknessesSelected,
//            onFilterSelected = {
//                if (weaknessesSelected.contains(it)) {
//                    weaknessesSelected.remove(it)
//                } else {
//                    weaknessesSelected.add(it)
//                }
//            }
//        )
//
//        FilterSection(
//            title = R.string.heights,
//            items = heightList,
//            itemsSelected = heightsSelected,
//            onFilterSelected = {
//                if (heightsSelected.contains(it)) {
//                    heightsSelected.remove(it)
//                } else {
//                    heightsSelected.add(it)
//                }
//            }
//        )
//
//        FilterSection(
//            title = R.string.weights,
//            items = weightList,
//            itemsSelected = weightsSelected,
//            onFilterSelected = {
//                if (weightsSelected.contains(it)) {
//                    weightsSelected.remove(it)
//                } else {
//                    weightsSelected.add(it)
//                }
//            }
//        )
//
//        Row(
//            horizontalArrangement = Arrangement.spacedBy(16.dp),
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp)
//        ) {
//            Button(
//                onClick = { /*TODO*/ },
//                colors = ButtonDefaults.buttonColors(
//                    backgroundColor = MaterialTheme.colors.secondaryInput,
//                    contentColor = MaterialTheme.colors.secondaryVariantInput
//                ),
//                shape = RoundedCornerShape(10.dp),
//                modifier = Modifier
//                    .height(60.dp)
//                    .weight(1f)
//            ) {
//                Text(
//                    text = stringResource(id = R.string.reset),
//                    style = MaterialTheme.typography.description,
//                    color = MaterialTheme.colors.primaryVariantText
//                )
//            }
//            Button(
//                onClick = { onFilterApplied() },
//                colors = ButtonDefaults.buttonColors(
//                    backgroundColor = MaterialTheme.colors.primaryInput,
//                    contentColor = MaterialTheme.colors.secondaryText
//                ),
//                shape = RoundedCornerShape(10.dp),
//                modifier = Modifier
//                    .height(60.dp)
//                    .weight(1f)
//            ) {
//                Text(
//                    text = stringResource(id = R.string.apply),
//                    style = MaterialTheme.typography.description
//                )
//            }
//        }
//        Spacer(modifier = Modifier.height(1.dp))
//    }
//}
