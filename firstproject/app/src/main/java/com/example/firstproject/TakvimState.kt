package com.example.firstproject

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.firstproject.model.Etkinlik
import java.time.LocalDate

data class TakvimState(
    var gozukenEtkinlikler : SnapshotStateList<Etkinlik> = mutableStateListOf<Etkinlik>(),
    var seciliTarih : MutableState<LocalDate> = mutableStateOf(LocalDate.now()),


    )