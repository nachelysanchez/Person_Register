package com.example.personregister.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Ocupaciones")
@Parcelize
data class Ocupacion(
    @PrimaryKey(autoGenerate = true)
    val OcupacionId: Int,
    val Nombres: String
): Parcelable