package com.montfel.pokfinder.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.montfel.pokfinder.core.designsystem.R
import com.montfel.pokfinder.core.designsystem.theme.PokfinderTheme
import com.montfel.pokfinder.core.designsystem.theme.primaryInput
import com.montfel.pokfinder.core.designsystem.theme.primaryText
import com.montfel.pokfinder.core.designsystem.theme.primaryVariantText
import com.montfel.pokfinder.core.designsystem.theme.secondaryInput
import com.montfel.pokfinder.core.designsystem.theme.textFieldIcon

@Composable
fun SearchField(
    text: String,
    onType: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = onType,
        maxLines = 1,
        shape = RoundedCornerShape(10.dp),
        textStyle = PokfinderTheme.typography.description,
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colors.textFieldIcon),
                modifier = Modifier.size(20.dp)
            )
        },
        trailingIcon = {
            if (text.isNotEmpty()) {
                IconButton(onClick = { onType("") }) {
                    Image(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(id = R.string.clear_text_field),
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.textFieldIcon),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.secondaryInput,
            textColor = MaterialTheme.colors.primaryText,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colors.primaryInput,
        ),
        placeholder = {
            Text(
                text = stringResource(id = R.string.placeholder_textfield),
                style = PokfinderTheme.typography.description,
                color = MaterialTheme.colors.primaryVariantText
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    )
}
