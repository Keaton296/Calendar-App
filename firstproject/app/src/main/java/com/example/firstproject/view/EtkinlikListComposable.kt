package com.example.firstproject.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.firstproject.TakvimEvent
import com.example.firstproject.TakvimViewModel
import com.example.firstproject.model.Etkinlik

@Composable
fun EtkinlikListComposable(viewModel: TakvimViewModel){
    LazyColumn(modifier = Modifier.fillMaxSize()){
        items(viewModel.etkinlikler.toList()){etkinlik -> EtkinlikCardComposable(etkinlik,viewModel) }
    }
}