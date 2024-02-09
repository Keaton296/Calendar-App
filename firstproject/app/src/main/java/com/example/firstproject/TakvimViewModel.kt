package com.example.firstproject

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstproject.model.Etkinlik
import com.example.firstproject.model.EtkinlikDao
import kotlinx.coroutines.launch
import java.time.LocalDate

class TakvimViewModel(private val dao: EtkinlikDao) : ViewModel(){

    var seciligun = mutableStateOf(LocalDate.now())
        private set
    var etkinlikler = mutableStateListOf<Etkinlik>()
        private set
    var etkinlikInputToggle = mutableStateOf(false)
        private set
    var etkinlikInputBilgi = mutableStateOf("")
    fun onEvent(event: TakvimEvent){
        when(event){
            is TakvimEvent.AddEtkinlik -> {
                viewModelScope.launch {
                    if(etkinlikInputBilgi.value != "")
                    {
                        dao.insertEtkinlik(Etkinlik(0,seciligun.value.year,seciligun.value.monthValue,seciligun.value.dayOfMonth,etkinlikInputBilgi.value))
                        etkinlikInputBilgi.value = ""
                        etkinlikInputToggle.value = false
                    }
                }
            }
            is TakvimEvent.DeleteEtkinlik -> {
                viewModelScope.launch { dao.delete(event.veri); etkinlikler.remove(event.veri)}
            }
            is TakvimEvent.GetEtkinliklerFromCurrentDate -> {
                viewModelScope.launch {
                    etkinlikler.clear()
                    val veriler = dao.getEtkinliklerByDate(seciligun.value.year,seciligun.value.monthValue,seciligun.value.dayOfMonth);
                    for (veri in veriler ){
                        etkinlikler.add(veri)
                    }
                }
            }
            is TakvimEvent.SelectTarih -> {
                seciligun.value = seciligun.value.plusDays((event.gun - seciligun.value.dayOfMonth).toLong())
            }
            TakvimEvent.NextAy -> {
                seciligun.value = seciligun.value.plusMonths(1).minusDays((seciligun.value.dayOfMonth -1).toLong())
            }
            TakvimEvent.PreviousAy -> {
                seciligun.value = seciligun.value.plusMonths(-1).minusDays((seciligun.value.dayOfMonth -1).toLong())
            }

            TakvimEvent.ToggleEtkinlikInputScreen -> {
                etkinlikInputToggle.value = !etkinlikInputToggle.value;
            }
        }
    }
}