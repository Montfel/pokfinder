package com.montfel.pokedex.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.montfel.pokedex.R
import com.montfel.pokedex.presentation.theme.Gray17
import com.montfel.pokedex.presentation.theme.Green62
import com.montfel.pokedex.presentation.theme.Green8B

@Composable
fun PokemonCards(results: List<String>) {
    LazyColumn(modifier = Modifier.padding(top = 20.dp)) {
        items(results) {
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
                            style = MaterialTheme.typography.h6,
                            color = Gray17,
                            modifier = Modifier.alpha(0.6f)
                        )
                        Text(
                            text = it,
                            style = MaterialTheme.typography.h3,
                            color = Color.White,
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
                                            modifier = Modifier.size(15.dp)
                                        )
                                        Text(
                                            text = "Grass",
                                            style = MaterialTheme.typography.subtitle1,
                                            color = Color.White,
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
    }
}
