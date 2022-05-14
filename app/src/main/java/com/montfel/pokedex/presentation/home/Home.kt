package com.montfel.pokedex.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.montfel.pokedex.R
import com.montfel.pokedex.presentation.theme.Gray17
import com.montfel.pokedex.presentation.theme.Gray74
import com.montfel.pokedex.presentation.theme.GrayF2
import com.montfel.pokedex.presentation.theme.RedEA

@Composable
fun Home(
    navController: NavController,
//    viewModel: HomeViewModel = hiltViewModel()
) {

//    val uiState by viewModel.uiState.collectAsState()
//
//    LaunchedEffect(key1 = Unit) {
//        viewModel.getHeight()
//    }

    Scaffold(
        topBar = { TopBar() }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
//                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "uiState.height.toString()",
                style = MaterialTheme.typography.h2,
                color = Gray17,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.subtitle),
                style = MaterialTheme.typography.h5,
                color = Gray74,
            )
            Spacer(modifier = Modifier.height(25.dp))
            CustomTextField()
            PokemonCards()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomTextField() {
    val text = remember { mutableStateOf("") }
    TextField(
        value = text.value,
        onValueChange = { text.value = it },
        shape = RoundedCornerShape(10.dp),
        textStyle = MaterialTheme.typography.h5,
        leadingIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
            )
        },
        colors = textFieldColors(
            backgroundColor = GrayF2,
            textColor = Gray17,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = RedEA,
            focusedLabelColor = Color.Blue,
        ),
        placeholder = {
            Text(
                text = stringResource(id = R.string.placeholder_textfield),
                style = MaterialTheme.typography.h5,
                color = Gray74
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
}

