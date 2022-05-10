package com.example.personregister.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Personas")
data class Persona(
    @PrimaryKey(autoGenerate = true)
    val PersonaId: Int,
    val Nombres: String,
    val Balance: Float
)