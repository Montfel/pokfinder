package com.montfel.pokfinder.feature.home.ui.bottomsheet.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.montfel.pokfinder.core.designsystem.theme.PokfinderTheme

@Composable
fun SortButton(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    isEnabled: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor =
        if (isEnabled) PokfinderTheme.palette.primaryInput
        else PokfinderTheme.palette.secondaryInput
    val textColor =
        if (isEnabled) PokfinderTheme.palette.secondaryText
        else PokfinderTheme.palette.primaryVariantText

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = PokfinderTheme.palette.secondaryVariantInput
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = modifier.height(60.dp)
    ) {
        Text(
            text = stringResource(id = title),
            style = PokfinderTheme.typography.description,
            color = textColor
        )
    }
}
