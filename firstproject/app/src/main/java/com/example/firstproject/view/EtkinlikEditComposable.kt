package com.example.firstproject.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.firstproject.TakvimEvent
import com.example.firstproject.TakvimViewModel
import com.example.firstproject.model.Etkinlik

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EtkinlikEditComposable(innerPadding : PaddingValues,viewModel: TakvimViewModel){

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(innerPadding)) {
        Text("Etkinlik bilgisini giriniz, etkinlik, seçili tarih için eklenecektir.",
            modifier = Modifier.padding(all = 10.dp),
            fontStyle = FontStyle.Italic
        )
        TextField(
            value = viewModel.etkinlikInputBilgi.value,
            onValueChange =  {viewModel.etkinlikInputBilgi.value = it},
            label = {Text("Etkinlik Bilgisi")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 10.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.hsv(27f,1f,1f),
                focusedLabelColor = Color.hsv(27f,1f,1f))

        )
        Button(
            onClick = { viewModel.onEvent(TakvimEvent.AddEtkinlik) }, //buraya viewModel.onEvent(EtkinlikEvent.EtkinlikEkle)
            modifier= Modifier.padding(all = 10.dp),
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.hsv(27f,1f,1f)
            )
        ) {
            Text("Ekle")
        }
    }

}