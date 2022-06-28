package com.montfel.pokedex.presentation.bottomsheet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.montfel.pokedex.R
import com.montfel.pokedex.presentation.components.BottomSheetButton
import com.montfel.pokedex.presentation.components.BottomSheetHeader

enum class SortOptions {
    SmallestNumber, HighestNumber, Alphabetical, ReverseAlphabetical
}

@Composable
fun SortBottomSheet(sortOption: (SortOptions) -> Unit) {
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
            BottomSheetButton(
                title = R.string.smallest,
                modifier = Modifier.fillMaxWidth(),
                onClick = { sortOption(SortOptions.SmallestNumber) }
            )
            BottomSheetButton(
                title = R.string.highest,
                modifier = Modifier.fillMaxWidth(),
                onClick = { sortOption(SortOptions.HighestNumber) }
            )
            BottomSheetButton(
                title = R.string.a_z,
                modifier = Modifier.fillMaxWidth(),
                onClick = { sortOption(SortOptions.Alphabetical)}
            )
            BottomSheetButton(
                title = R.string.z_a,
                modifier = Modifier.fillMaxWidth(),
                onClick = { sortOption(SortOptions.ReverseAlphabetical) }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}
