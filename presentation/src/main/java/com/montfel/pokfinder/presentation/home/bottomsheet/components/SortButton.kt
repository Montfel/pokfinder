package com.montfel.pokfinder.presentation.home.bottomsheet.components

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
import com.montfel.pokfinder.designsystem.theme.PokfinderTheme
import com.montfel.pokfinder.designsystem.theme.primaryInput
import com.montfel.pokfinder.designsystem.theme.primaryVariantText
import com.montfel.pokfinder.designsystem.theme.secondaryInput
import com.montfel.pokfinder.designsystem.theme.secondaryText
import com.montfel.pokfinder.designsystem.theme.secondaryVariantInput

@Composable
fun SortButton(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    isEnabled: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor =
        if (isEnabled) MaterialTheme.colors.primaryInput
        else MaterialTheme.colors.secondaryInput
    val textColor =
        if (isEnabled) MaterialTheme.colors.secondaryText
        else MaterialTheme.colors.primaryVariantText

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = MaterialTheme.colors.secondaryVariantInput
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
