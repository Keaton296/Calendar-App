package com.example.firstproject

import com.example.firstproject.model.Etkinlik

sealed interface TakvimEvent {
    object GetEtkinliklerFromCurrentDate : TakvimEvent
    data class DeleteEtkinlik(val veri: Etkinlik) : TakvimEvent
    object AddEtkinlik : TakvimEvent
    data class SelectTarih(val gun: Int) : TakvimEvent
    object ToggleEtkinlikInputScreen : TakvimEvent
    object NextAy : TakvimEvent
    object PreviousAy : TakvimEvent
}