package com.example.firstproject.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.firstproject.TakvimEvent
import com.example.firstproject.TakvimViewModel
import com.example.firstproject.model.Etkinlik

@Composable
fun EtkinlikCardComposable(veri: Etkinlik,viewModel: TakvimViewModel){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(75.dp)
        .padding(5.dp)
        .shadow(5.dp, shape = RoundedCornerShape(8.dp)),color = Color.White,) {
        Column(modifier = Modifier
            .height(75.dp)
            .fillMaxWidth(),verticalArrangement = Arrangement.Center) {
            Row(modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = veri.bilgi.toString(), textAlign = TextAlign.Left)
                Button(onClick = {viewModel.onEvent(TakvimEvent.DeleteEtkinlik(veri))},colors = ButtonDefaults.buttonColors(containerColor = Color.hsv(27f,.58f,1f))){
                    Image(imageVector = Icons.Default.Delete, contentDescription = "sil")
                }

            }
        }

    }
}