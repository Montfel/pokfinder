package com.montfel.pokfinder.feature.home.ui.bottomsheet.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.montfel.pokfinder.core.common.domain.model.Type
import com.montfel.pokfinder.core.designsystem.R
import com.montfel.pokfinder.core.designsystem.theme.PokfinderTheme
import com.montfel.pokfinder.feature.home.ui.bottomsheet.components.BottomSheetHeader
import com.montfel.pokfinder.feature.home.ui.bottomsheet.components.FilterSection

@Composable
fun FilterBottomSheet(
    types: List<Type>,
    onFilterApplied: (types: List<Type>) -> Unit,
    viewModel: FilterViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 50.dp)
            .verticalScroll(rememberScrollState())
    ) {
        BottomSheetHeader(
            title = R.string.filters,
            description = R.string.filters_description,
            Modifier.padding(horizontal = 16.dp)
        )

        FilterSection(
            title = R.string.types,
            items = types,
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
                    containerColor = PokfinderTheme.palette.secondaryInput,
                    contentColor = PokfinderTheme.palette.secondaryVariantInput
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .height(60.dp)
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.reset),
                    style = PokfinderTheme.typography.description,
                    color = PokfinderTheme.palette.primaryVariantText
                )
            }

            Button(
                onClick = { onFilterApplied(uiState.selectedTypes) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = PokfinderTheme.palette.primaryInput,
                    contentColor = PokfinderTheme.palette.secondaryText
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
