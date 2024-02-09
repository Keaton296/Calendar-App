package com.example.firstproject.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EtkinlikDao {

    @Query("SELECT * FROM Etkinlik WHERE yil IN (:yill) AND ay IN (:ayy) AND gun IN (:gunn)")
    suspend fun getEtkinliklerByDate(yill: Int, ayy: Int,gunn:Int): List<Etkinlik>
    @Delete
    suspend fun delete(veri: Etkinlik)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEtkinlik(veri: Etkinlik)

}