package com.example.firstproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.firstproject.model.Etkinlik
import com.example.firstproject.model.EtkinlikDatabase
import com.example.firstproject.view.AnaEkranComposable
import com.example.firstproject.view.EtkinlikCardComposable

val haftagunleri = arrayOf("PZT","SAL","ÇAR","PER","CUM","CMT","PAZ")
val ayIsimleri = arrayOf("Ocak","Şubat","Mart","Nisan","Mayıs","Haziran","Temmuz","Ağustos","Eylül","Ekim","Kasım","Aralık")

class MainActivity : ComponentActivity() {
    companion object {
        lateinit var db: EtkinlikDatabase
            private set
    }
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        db = Room.databaseBuilder(applicationContext, EtkinlikDatabase::class.java,"database-etkinlikler").build()
        val viewModel by viewModels<TakvimViewModel> (
            factoryProducer = {
                object : ViewModelProvider.Factory {
                    override fun<T: ViewModel> create(modelClass: Class<T>): T {
                        return TakvimViewModel(db.dao) as T
                    }
                }
            }
        )
        super.onCreate(savedInstanceState)
        setContent{
            AnaEkranComposable(viewModel)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    //AnaEkranComposable()
}