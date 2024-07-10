package com.montfel.pokfinder.feature.home.ui.bottomsheet.sort

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.montfel.pokfinder.core.designsystem.R
import com.montfel.pokfinder.feature.home.domain.model.SortOptions
import com.montfel.pokfinder.feature.home.ui.bottomsheet.components.BottomSheetHeader
import com.montfel.pokfinder.feature.home.ui.bottomsheet.components.SortButton

@Composable
fun SortBottomSheet(
    sortOptionSelected: SortOptions,
    onSortOptionSelected: (SortOptions) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 50.dp)
            .verticalScroll(rememberScrollState())
    ) {
        BottomSheetHeader(
            title = R.string.sort,
            description = R.string.sort_description
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            SortButton(
                title = R.string.smallest,
                isEnabled = sortOptionSelected is SortOptions.SmallestNumber,
                onClick = { onSortOptionSelected(SortOptions.SmallestNumber) },
                modifier = Modifier.fillMaxWidth()
            )

            SortButton(
                title = R.string.highest,
                isEnabled = sortOptionSelected is SortOptions.HighestNumber,
                onClick = { onSortOptionSelected(SortOptions.HighestNumber) },
                modifier = Modifier.fillMaxWidth()
            )

            SortButton(
                title = R.string.a_z,
                isEnabled = sortOptionSelected is SortOptions.Alphabetical,
                onClick = { onSortOptionSelected(SortOptions.Alphabetical) },
                modifier = Modifier.fillMaxWidth()
            )

            SortButton(
                title = R.string.z_a,
                isEnabled = sortOptionSelected is SortOptions.ReverseAlphabetical,
                onClick = { onSortOptionSelected(SortOptions.ReverseAlphabetical) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
