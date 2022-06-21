package com.montfel.pokedex.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.montfel.pokedex.presentation.theme.Gray74
import com.montfel.pokedex.presentation.theme.GrayF2
import com.montfel.pokedex.presentation.theme.description

@Composable
fun BottomSheetButton(
    @StringRes title: Int,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = GrayF2,
            contentColor = Gray74
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = modifier.height(60.dp)
    ) {
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.description
        )
    }
}
