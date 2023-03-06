package com.montfel.pokfinder.presentation.home.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.montfel.pokfinder.R
import com.montfel.pokfinder.presentation.home.bottomsheet.components.BottomSheetHeader
import com.montfel.pokfinder.presentation.home.bottomsheet.components.SortButton

@Composable
fun SortBottomSheet(
    sortOptionSelected: SortOptions,
    onSortOptionSelected: (SortOptions) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 30.dp)
            .verticalScroll(rememberScrollState())
    ) {
        BottomSheetHeader(
            title = R.string.sort,
            description = R.string.sort_description
        )

        Spacer(modifier = Modifier.height(32.dp))

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

        Spacer(modifier = Modifier.height(32.dp))
    }
}
