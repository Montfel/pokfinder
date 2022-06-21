package com.montfel.pokedex.presentation.bottomsheet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.montfel.pokedex.R
import com.montfel.pokedex.presentation.components.BottomSheetButton
import com.montfel.pokedex.presentation.components.BottomSheetHeader
import com.montfel.pokedex.presentation.theme.Gray74
import com.montfel.pokedex.presentation.theme.GrayF2
import com.montfel.pokedex.presentation.theme.TypePsychic
import com.montfel.pokedex.presentation.theme.description

@Composable
fun SortBottomSheet() {
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(vertical = 30.dp)
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
            BottomSheetButton(
                title = R.string.smallest,
                modifier = Modifier.fillMaxWidth()
            )
            BottomSheetButton(
                title = R.string.highest,
                modifier = Modifier.fillMaxWidth()
            )
            BottomSheetButton(
                title = R.string.a_z,
                modifier = Modifier.fillMaxWidth()
            )
            BottomSheetButton(
                title = R.string.z_a,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
