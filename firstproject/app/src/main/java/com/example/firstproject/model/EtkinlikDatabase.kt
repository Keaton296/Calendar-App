package com.example.firstproject.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.firstproject.model.Etkinlik
import com.example.firstproject.model.EtkinlikDao

@Database(entities = [Etkinlik::class],
    version = 1)
abstract class EtkinlikDatabase : RoomDatabase(){
    abstract val dao: EtkinlikDao
}