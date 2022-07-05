package com.montfel.pokedex.presentation.bottomsheet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.montfel.pokedex.R
import com.montfel.pokedex.presentation.components.BottomSheetHeader
import com.montfel.pokedex.presentation.components.SortButton

enum class SortOptions {
    SmallestNumber, HighestNumber, Alphabetical, ReverseAlphabetical
}

@Composable
fun SortBottomSheet(
    sortSelectedOption: SortOptions,
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
                isEnabled = sortSelectedOption == SortOptions.SmallestNumber,
                onClick = { onSortOptionSelected(SortOptions.SmallestNumber) },
                modifier = Modifier.fillMaxWidth()
            )
            SortButton(
                title = R.string.highest,
                isEnabled = sortSelectedOption == SortOptions.HighestNumber,
                onClick = { onSortOptionSelected(SortOptions.HighestNumber) },
                modifier = Modifier.fillMaxWidth()
            )
            SortButton(
                title = R.string.a_z,
                isEnabled = sortSelectedOption == SortOptions.Alphabetical,
                onClick = { onSortOptionSelected(SortOptions.Alphabetical) },
                modifier = Modifier.fillMaxWidth()
            )
            SortButton(
                title = R.string.z_a,
                isEnabled = sortSelectedOption == SortOptions.ReverseAlphabetical,
                onClick = { onSortOptionSelected(SortOptions.ReverseAlphabetical) },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}
