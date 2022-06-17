package com.montfel.pokedex.presentation.bottomsheet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.montfel.pokedex.R
import com.montfel.pokedex.presentation.components.TypeEffectivenessItem
import com.montfel.pokedex.presentation.theme.*

@Composable
fun FilterBottomSheet() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 30.dp)
            .padding(bottom = 50.dp)
    ) {
        Text(
            text = stringResource(id = R.string.filters),
            style = MaterialTheme.typography.pokemonName,
            color = Gray17,
        )
        Text(
            text = stringResource(id = R.string.filters_description),
            style = MaterialTheme.typography.description,
            color = Gray74,
        )

        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = stringResource(id = R.string.types),
            style = MaterialTheme.typography.filterTitle,
            color = Gray17,
        )
        Row() {
            TypeEffectivenessItem(typeColor = TypeFire, image = R.drawable.ic_fire)
        }

        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = stringResource(id = R.string.heights),
            style = MaterialTheme.typography.filterTitle,
            color = Gray17,
        )
        Row() {
            TypeEffectivenessItem(typeColor = TypeFire, image = R.drawable.ic_fire)
        }

        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = stringResource(id = R.string.weights),
            style = MaterialTheme.typography.filterTitle,
            color = Gray17,
        )
        Row() {
            TypeEffectivenessItem(typeColor = TypeFire, image = R.drawable.ic_fire)
        }

        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = stringResource(id = R.string.number_range),
            style = MaterialTheme.typography.filterTitle,
            color = Gray17,
        )

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = GrayF2,
                    contentColor = Gray74
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .height(60.dp)
                    .width(160.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.reset),
                    style = MaterialTheme.typography.description
                )
            }
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = TypePsychic,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .height(60.dp)
                    .width(160.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.apply),
                    style = MaterialTheme.typography.description
                )
            }
        }
    }
}

