package com.example.firstproject.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TakvimGunuComposable(etkinlikColor: Color, textContent : String, hasEtkinlik: Boolean, onClick: () -> Unit)
{
    if (hasEtkinlik)
    {
        Surface(modifier = Modifier.aspectRatio(1f),
            color = etkinlikColor,
            shape = RoundedCornerShape(60.dp),
            onClick = onClick
        )
        {
            Column(verticalArrangement = Arrangement.Center)
            {
                Text(text = textContent,textAlign = TextAlign.Center, fontSize = 25.sp,modifier = Modifier.fillMaxWidth(), fontWeight = FontWeight.Light)
            }
        }
    }
    else
    {
        Surface(modifier = Modifier.aspectRatio(1f),
            shape = RoundedCornerShape(60.dp),
            onClick = onClick)
        {
            Column(verticalArrangement = Arrangement.Center)
            {
                Text(text = textContent,textAlign = TextAlign.Center,modifier = Modifier.fillMaxWidth(), fontSize = 25.sp, fontWeight = FontWeight.Light)
            }
        }
    }

}