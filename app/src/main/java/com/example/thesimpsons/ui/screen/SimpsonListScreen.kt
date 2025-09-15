package com.example.thesimpsons.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.thesimpsons.data.Simpson
import com.example.thesimpsons.viewmodel.SimpsonsViewmodel

@Composable
fun SimpsonListScreen(viewmodel: SimpsonsViewmodel) {
    val simpsons by viewmodel.allSimpson.collectAsState()

    LazyColumn {
        items(simpsons) { simpson ->
            SimpsonCard(simpson)
        }
    }
}

@Composable
fun SimpsonCard(simpson: Simpson) {
    val baseUrl = "https://cdn.thesimpsonsapi.com/500"
    val imageUrl = baseUrl + simpson.portrait_path
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = imageUrl
            ),
            contentDescription = "Simpson Portrait",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
        )
        Text(
            "Name : ${simpson.name}", modifier = Modifier.alpha(0.7f),
            fontFamily = FontFamily.Monospace
        )
        Text(
            "Status : ${simpson.status}", modifier = Modifier.alpha(0.7f),
            fontFamily = FontFamily.Monospace
        )
    }
}
