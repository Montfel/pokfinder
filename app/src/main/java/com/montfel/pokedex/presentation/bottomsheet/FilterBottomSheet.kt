package com.montfel.pokedex.presentation.bottomsheet

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.montfel.pokedex.R
import com.montfel.pokedex.presentation.components.BottomSheetHeader
import com.montfel.pokedex.presentation.components.FilterItem
import com.montfel.pokedex.presentation.theme.*

@Composable
fun FilterBottomSheet() {
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(vertical = 30.dp)
            .verticalScroll(rememberScrollState())
    ) {
        BottomSheetHeader(
            title = R.string.filters,
            description = R.string.filters_description
        )

        FilterSection(title = R.string.types)

        FilterSection(title = R.string.weakeness)

        FilterSection(title = R.string.heights)

        FilterSection(title = R.string.weights)

        FilterRange()

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

@Composable
fun FilterSection(
    @StringRes title: Int,
//    items: List<Any>
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            text = stringResource(id = title),
            style = MaterialTheme.typography.filterTitle,
            color = Gray17,
        )
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
//            items.forEach {
                FilterItem(icon = R.drawable.ic_fire, typeColor = TypeFire)
//            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterRange() {
    val range = 1f..100f
    var sliderPosition by remember { mutableStateOf(range) }

    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        Text(
            text = stringResource(id = R.string.number_range),
            style = MaterialTheme.typography.filterTitle,
            color = Gray17,
        )

        RangeSlider(
            values = sliderPosition,
            onValueChange = { sliderPosition = it },
            onValueChangeFinished = {},
            colors = SliderDefaults.colors(
                activeTrackColor = TypePsychic,
                inactiveTrackColor = GrayF2,
                thumbColor = TypePsychic
            )
        )
    }
}
