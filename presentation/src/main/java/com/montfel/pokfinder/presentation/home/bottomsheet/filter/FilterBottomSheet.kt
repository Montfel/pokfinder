package com.montfel.pokfinder.presentation.home.bottomsheet.filter

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.montfel.pokfinder.designsystem.R
import com.montfel.pokfinder.designsystem.model.AssetFromType
import com.montfel.pokfinder.designsystem.theme.PokfinderTheme
import com.montfel.pokfinder.presentation.home.bottomsheet.components.BottomSheetHeader
import com.montfel.pokfinder.presentation.home.bottomsheet.components.FilterSection
import com.montfel.pokfinder.designsystem.theme.primaryInput
import com.montfel.pokfinder.designsystem.theme.primaryVariantText
import com.montfel.pokfinder.designsystem.theme.secondaryInput
import com.montfel.pokfinder.designsystem.theme.secondaryText
import com.montfel.pokfinder.designsystem.theme.secondaryVariantInput

@Composable
fun FilterBottomSheet(
    assetFromTypeList: List<AssetFromType>,
    onFilterApplied: (List<AssetFromType>) -> Unit,
    viewModel: FilterViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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
            itemsSelected = uiState.selectedTypes,
            onFilterSelected = { viewModel.previewSelectedTypes(it) }
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Button(
                onClick = {
                    viewModel.resetFilters()
                    onFilterApplied(emptyList())
                },
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
                    style = PokfinderTheme.typography.description,
                    color = MaterialTheme.colors.primaryVariantText
                )
            }

            Button(
                onClick = { onFilterApplied(uiState.selectedTypes) },
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
                    style = PokfinderTheme.typography.description
                )
            }
        }
    }
}
