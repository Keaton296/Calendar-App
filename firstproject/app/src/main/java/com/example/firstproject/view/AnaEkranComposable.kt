package com.example.firstproject.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.firstproject.MainActivity
import com.example.firstproject.TakvimEvent
import com.example.firstproject.TakvimViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnaEkranComposable(viewModel : TakvimViewModel){
    Surface {
        Scaffold(
            topBar = {
                TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.hsv(27f,1f,1f),
                    titleContentColor = Color.White
                ),
                    title = {
                        Text("Takvim - Kaan Dalkıran")
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {viewModel.onEvent(TakvimEvent.ToggleEtkinlikInputScreen)}) { // Navigate to Adding Screen
                    Icon(Icons.Default.Add, contentDescription = "ToggleEtkinlikInputScreen")
                }
            }
        ) { innerPadding ->
            if(viewModel.etkinlikInputToggle.value)
            {
                EtkinlikEditComposable(innerPadding = innerPadding, viewModel = viewModel)
            }
            else
            {
                TakvimComposable(innerPadding, MainActivity.db,viewModel)
            }

        }
    }
}