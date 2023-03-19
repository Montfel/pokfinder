package com.montfel.pokfinder.presentation.home.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.montfel.pokfinder.R
import com.montfel.pokfinder.domain.AssetFromType
import com.montfel.pokfinder.presentation.home.bottomsheet.components.BottomSheetHeader
import com.montfel.pokfinder.presentation.home.bottomsheet.components.FilterSection
import com.montfel.pokfinder.presentation.theme.description
import com.montfel.pokfinder.presentation.theme.primaryInput
import com.montfel.pokfinder.presentation.theme.primaryVariantText
import com.montfel.pokfinder.presentation.theme.secondaryInput
import com.montfel.pokfinder.presentation.theme.secondaryText
import com.montfel.pokfinder.presentation.theme.secondaryVariantInput

@Composable
fun FilterBottomSheet(
    assetFromTypeList: List<AssetFromType>,
    typesSelected: MutableList<AssetFromType>,
    onFilterApplied: (MutableList<AssetFromType>) -> Unit
) {
//    val heightList = listOf(
//        AssetFromType.getAsset("short"),
//        AssetFromType.getAsset("medium_height"),
//        AssetFromType.getAsset("tall"),
//    )
//
//    val weightList = listOf(
//        AssetFromType.getAsset("light"),
//        AssetFromType.getAsset("normal_weight"),
//        AssetFromType.getAsset("heavy"),
//    )

    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp, bottom = 50.dp)
            .verticalScroll(rememberScrollState())
    ) {
        BottomSheetHeader(
            title = R.string.filters,
            description = R.string.filters_description,
            Modifier.padding(horizontal = 16.dp)
        )

        FilterSection(
            title = R.string.types,
            items = assetFromTypeList,
            itemsSelected = typesSelected,
            onFilterSelected = {
                if (typesSelected.contains(it)) {
                    typesSelected.remove(it)
                } else {
                    typesSelected.add(it)
                }
            }
        )

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

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Button(
                onClick = { typesSelected.clear().also { onFilterApplied(typesSelected) } },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondaryInput,
                    contentColor = MaterialTheme.colors.secondaryVariantInput
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .height(60.dp)
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.reset),
                    style = MaterialTheme.typography.description,
                    color = MaterialTheme.colors.primaryVariantText
                )
            }

            Button(
                onClick = { onFilterApplied(typesSelected) },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primaryInput,
                    contentColor = MaterialTheme.colors.secondaryText
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .height(60.dp)
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.apply),
                    style = MaterialTheme.typography.description
                )
            }
        }
    }
}
