package com.example.firstproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
data class Etkinlik(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "yil") val yil: Int,
    @ColumnInfo(name = "ay") val ay: Int,
    @ColumnInfo(name = "gun") val gun: Int,
    @ColumnInfo(name = "bilgi") val bilgi: String?
)