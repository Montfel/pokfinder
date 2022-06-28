package com.montfel.pokedex.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.montfel.pokedex.presentation.theme.Gray74
import com.montfel.pokedex.presentation.theme.GrayF2
import com.montfel.pokedex.presentation.theme.TypePsychic
import com.montfel.pokedex.presentation.theme.description

@Composable
fun BottomSheetButton(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    var isEnabled by remember { mutableStateOf(false) }
    val backgroundColor = if (isEnabled) TypePsychic else GrayF2
    val contentColor = if (isEnabled) Color.White else Gray74

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
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
