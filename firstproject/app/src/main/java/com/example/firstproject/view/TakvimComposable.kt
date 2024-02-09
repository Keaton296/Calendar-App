package com.example.firstproject.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.firstproject.TakvimEvent
import com.example.firstproject.TakvimViewModel
import com.example.firstproject.ayIsimleri
import com.example.firstproject.haftagunleri
import com.example.firstproject.model.Etkinlik
import com.example.firstproject.model.EtkinlikDatabase
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TakvimComposable(padding : PaddingValues, databaseRef: EtkinlikDatabase,viewModel: TakvimViewModel){
    var _darkColor = Color.hsv(132f,.01f,.77f);
    var etkColor = Color.hsv(27f,.65f,1f);
    Column(Modifier.padding(padding))
    {
        Text(viewModel.seciligun.value.year.toString(),modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,fontSize = TextUnit(25f, TextUnitType.Sp))
        Row(horizontalArrangement = Arrangement.SpaceBetween , modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(), verticalAlignment = Alignment.CenterVertically)
        {
            Surface(color = _darkColor,modifier = Modifier
                .height(30.dp)
                .width(30.dp),
                shape = RoundedCornerShape(30.dp),
                onClick = {viewModel.onEvent(TakvimEvent.PreviousAy)}) {
                Text("<",modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight())
            }
            Text(ayIsimleri[viewModel.seciligun.value.monthValue -1], fontSize = TextUnit(40f, TextUnitType.Sp))
            Surface(color = _darkColor,modifier = Modifier
                .height(30.dp)
                .width(30.dp),
                shape = RoundedCornerShape(30.dp),
                onClick = {viewModel.onEvent(TakvimEvent.NextAy)}) {
                Text(">",modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight())
            }
        }
        LazyVerticalGrid(columns = GridCells.Fixed(7))
        {
            //var gunlersiralamasi :Array<String> = haftagunleri.clone()
            viewModel.seciligun.value.dayOfWeek
            items(haftagunleri){ str -> Surface(color = _darkColor){ Text(str, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold,color= Color.Black) } }
            for (i in 1..viewModel.seciligun.value.lengthOfMonth()) {
                item {
                    val isEtkinlik = i == viewModel.seciligun.value.dayOfMonth
                    TakvimGunuComposable(etkinlikColor = etkColor,
                        "${i}",
                        isEtkinlik,
                        onClick = {viewModel.onEvent(TakvimEvent.SelectTarih(i))} )
                }
            }
        }
        LaunchedEffect(key1 = viewModel.seciligun.value) {
            viewModel.onEvent(TakvimEvent.GetEtkinliklerFromCurrentDate)
        }
        EtkinlikListComposable(viewModel)

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TakvimComposable(padding : PaddingValues){
    var seciliGun by remember{ mutableStateOf(LocalDate.now())};
    var _darkColor = Color.hsv(132f,.01f,.77f);
    var etkColor = Color.hsv(27f,.65f,1f);
    Column(Modifier.padding(padding))
    {
        Text(seciliGun.year.toString(),modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,fontSize = TextUnit(25f, TextUnitType.Sp))
        Row(horizontalArrangement = Arrangement.SpaceBetween , modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(), verticalAlignment = Alignment.CenterVertically)
        {// Ay + Yıl info
            Surface(color = _darkColor,modifier = Modifier
                .height(30.dp)
                .width(30.dp),
                shape = RoundedCornerShape(30.dp),
                onClick = {seciliGun = seciliGun.plusMonths(-1).minusDays((seciliGun.dayOfMonth -1).toLong())}) {
                Text("<",modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight())
            }
            Text(ayIsimleri[seciliGun.monthValue -1], fontSize = TextUnit(40f, TextUnitType.Sp))
            Surface(color = _darkColor,modifier = Modifier
                .height(30.dp)
                .width(30.dp),
                shape = RoundedCornerShape(30.dp),
                onClick = {seciliGun = seciliGun.plusMonths(1).minusDays((seciliGun.dayOfMonth -1).toLong())}) {
                Text(">",modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight())
            }
        }
        LazyVerticalGrid(columns = GridCells.Fixed(7))
        {
            items(haftagunleri){ str -> Surface(color = _darkColor){Text(str, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold,color= Color.Black)}}
            for (i in 1..seciliGun.lengthOfMonth()) {
                item {
                    val isEtkinlik = i == seciliGun.dayOfMonth
                    TakvimGunuComposable(etkinlikColor = etkColor,
                        "${i}",
                        isEtkinlik,
                        onClick = {seciliGun = seciliGun.plusDays((i - seciliGun.dayOfMonth).toLong())})
                }
            }
        }
        //EtkinlikList(databaseRef.dao.getEtkinliklerByDate(seciliGun.year,seciliGun.monthValue,seciliGun.dayOfMonth))

    }
}