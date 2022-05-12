package com.example.personregister.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Ocupaciones")
data class Ocupacion(
    @PrimaryKey(autoGenerate = true)
    val OcupacionId: Int,
    val Nombres: String
)