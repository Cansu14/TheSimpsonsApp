package com.example.thesimpsons.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.foundation.clickable
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import com.example.thesimpsons.ui.theme.softYellow
import androidx.compose.ui.text.font.FontFamily
import coil.compose.rememberAsyncImagePainter
import com.example.thesimpsons.data.Simpson
import com.example.thesimpsons.ui.theme.Dimens.lazyColumnPad
import com.example.thesimpsons.ui.theme.Dimens.listCardHeight
import com.example.thesimpsons.ui.theme.Dimens.midPad
import com.example.thesimpsons.ui.theme.Dimens.textAlpha
import com.example.thesimpsons.ui.theme.softPink
import com.example.thesimpsons.viewmodel.SimpsonsViewmodel

@Composable
fun SimpsonListScreen(viewmodel: SimpsonsViewmodel, onClick: (Int) -> Unit) {
    val simpsons by viewmodel.allSimpson.collectAsState()

    LazyColumn(modifier = Modifier.background(softYellow)
        .padding(bottom = lazyColumnPad)) {
        items(simpsons) { simpson ->
            SimpsonCard(simpson, onClick)
        }
    }
}

@Composable
fun SimpsonCard(simpson: Simpson, onClick: (Int) -> Unit) {
    val baseUrl = "https://cdn.thesimpsonsapi.com/500"
    val imageUrl = baseUrl + simpson.portrait_path
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(midPad)
            .clickable { onClick(simpson.id) },
        colors = CardDefaults.cardColors(softPink)
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = imageUrl
            ),
            contentDescription = "Simpson Portrait",
            modifier = Modifier
                .fillMaxWidth()
                .height(listCardHeight),
        )
        Text(
            "Name : ${simpson.name}", modifier = Modifier.alpha(textAlpha),
            fontFamily = FontFamily.Monospace
        )
        Text(
            "Status : ${simpson.status}", modifier = Modifier.alpha(textAlpha),
            fontFamily = FontFamily.Monospace
        )
    }
}
