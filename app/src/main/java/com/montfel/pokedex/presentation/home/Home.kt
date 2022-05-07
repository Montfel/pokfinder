package com.montfel.pokedex.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.montfel.pokedex.R
import com.montfel.pokedex.presentation.theme.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Home() {
    Scaffold(
        topBar = {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_generation),
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sort),
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_filter),
                            contentDescription = null
                        )
                    }

                }
            }
        }
    ) {
        Column(modifier = Modifier.padding(horizontal = 28.dp)) {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h1,
                color = Gray17,
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = stringResource(id = R.string.subtitle),
                style = MaterialTheme.typography.h3,
                color = Gray74,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(25.dp))

            val text = remember { mutableStateOf("") }

            TextField(
                value = text.value,
                onValueChange = { text.value = it },
                shape = RoundedCornerShape(10.dp),
                textStyle = MaterialTheme.typography.h4,
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                    )
                },
                colors = textFieldColors(
                    backgroundColor = GrayF2,
                    textColor = Gray74,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Gray74
                ),
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.placeholder_textfield),
                        style = MaterialTheme.typography.h3,
                        fontSize = 16.sp
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn {
                item {
                    Box(modifier = Modifier.height(140.dp)) {
                        Card(
                            shape = RoundedCornerShape(10.dp),
                            backgroundColor = Green8B,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(115.dp)
                                .align(Alignment.BottomCenter)
                        ) {
                            Column(modifier = Modifier.padding(20.dp)) {
                                Text(
                                    text = "#001",
                                    style = MaterialTheme.typography.h1,
                                    color = Gray17,
                                    fontSize = 12.sp,
                                    modifier = Modifier.alpha(0.6f)
                                )
                                Text(
                                    text = "Bulbasaur",
                                    style = MaterialTheme.typography.h1,
                                    color = Color.White,
                                    fontSize = 26.sp,
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                LazyRow {
                                    item {
                                        Card(
                                            shape = RoundedCornerShape(3.dp),
                                            backgroundColor = Green62,
                                            modifier = Modifier.height(25.dp)
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            ) {
                                                Image(
                                                    painter = painterResource(id = R.drawable.ic_grass),
                                                    contentDescription = null,
                                                    modifier = Modifier.padding(vertical = 5.dp)
                                                )
                                                Text(
                                                    text = "Grass",
                                                    style = MaterialTheme.typography.h2,
                                                    color = Color.White,
                                                    fontSize = 12.sp
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        Image(
                            painter = painterResource(id = R.drawable.ic_pokeball),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .size(145.dp)
                                .offset(x = 15.dp, y = 15.dp)
                                .alpha(0.3f)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_6x3),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .offset(x = (-37).dp, y = (-18).dp)
                                .width(74.dp)
                                .height(32.dp)
                                .alpha(0.3f)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.bulba),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(end = 10.dp, bottom = 10.dp)
                                .size(130.dp)
                        )
                    }
                }
                item {
                    Box(modifier = Modifier.height(140.dp)) {
                        Card(
                            shape = RoundedCornerShape(10.dp),
                            backgroundColor = OrangeFF,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(115.dp)
                                .align(Alignment.BottomCenter)
                        ) {
                            Column(modifier = Modifier.padding(20.dp)) {
                                Text(
                                    text = "#002",
                                    style = MaterialTheme.typography.h1,
                                    color = Gray17,
                                    fontSize = 12.sp,
                                    modifier = Modifier.alpha(0.6f)
                                )
                                Text(
                                    text = "Charmander",
                                    style = MaterialTheme.typography.h1,
                                    color = Color.White,
                                    fontSize = 26.sp,
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                LazyRow {
                                    item {
                                        Card(
                                            shape = RoundedCornerShape(3.dp),
                                            backgroundColor = OrangeFD,
                                            modifier = Modifier.height(25.dp)
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                                modifier = Modifier.padding(horizontal = 5.dp)
                                            ) {
                                                Image(
                                                    painter = painterResource(id = R.drawable.ic_fire),
                                                    contentDescription = null,
                                                    modifier = Modifier.padding(vertical = 5.dp)
                                                )
                                                Text(
                                                    text = "Fire",
                                                    style = MaterialTheme.typography.h2,
                                                    color = Color.White,
                                                    fontSize = 12.sp
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        Image(
                            painter = painterResource(id = R.drawable.ic_pokeball),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .size(145.dp)
                                .offset(x = 15.dp, y = 15.dp)
                                .alpha(0.3f)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.ic_6x3),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .offset(x = (-37).dp, y = (-18).dp)
                                .width(74.dp)
                                .height(32.dp)
                                .alpha(0.3f)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.charr),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(end = 10.dp, bottom = 10.dp)
                                .size(130.dp)
                        )
                    }
                }
            }
        }
    }
}

