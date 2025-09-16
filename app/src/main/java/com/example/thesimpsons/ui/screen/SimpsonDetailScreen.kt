package com.example.thesimpsons.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontFamily
import coil.compose.rememberAsyncImagePainter
import com.example.thesimpsons.ui.theme.Dimens.detailCardHeight
import com.example.thesimpsons.ui.theme.Dimens.textAlpha
import com.example.thesimpsons.ui.theme.Dimens.midPad
import com.example.thesimpsons.ui.theme.*
import com.example.thesimpsons.viewmodel.SimpsonsViewmodel

@Composable
fun SimpsonDetailScreen(viewmodel: SimpsonsViewmodel) {
    val simpson by viewmodel.simpson.collectAsState()

    val baseUrl = "https://cdn.thesimpsonsapi.com/500"
    val imageUrl = baseUrl + simpson?.portrait_path
    val simpsonInfo = listOf(
        "Id : " to simpson?.id,
        "Age : " to simpson?.age,
        "Name : " to simpson?.name,
        "Gender : " to simpson?.gender,
        "Occupation : " to simpson?.occupation,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(softYellow),
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.padding(midPad),
            colors = CardDefaults.cardColors(softPink)
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = imageUrl
                ),
                contentDescription = "Simpson Portrait",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(detailCardHeight),
            )
            simpsonInfo.forEach { (label, info) ->
                Row(modifier = Modifier.padding(start =midPad)) {
                    Text(label, fontFamily = FontFamily.Monospace, modifier = Modifier.alpha(textAlpha))
                    Text(info.toString(), fontFamily = FontFamily.Monospace)
                }

            }
        }
    }

}