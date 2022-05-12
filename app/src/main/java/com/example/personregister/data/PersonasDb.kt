package com.example.personregister.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.personregister.model.Ocupacion
import com.example.personregister.model.Persona

@Database(
    entities = [Persona::class, Ocupacion::class],
    version = 2
)
abstract class PersonasDb : RoomDatabase(){
    abstract  val personaDao: PersonaDao
    abstract  val ocupacionDao : OcupacionDao
    /*companion object {
        private const val DATABASE_NAME = "PersonasDb"
        @Volatile private var instance: PersonasDb? = null

        private fun buildDataBase(context: Context): PersonasDb {
            return Room.databaseBuilder(
                context.applicationContext,
                PersonasDb::class.java,
                DATABASE_NAME
            ).build()
        }

        fun getInstance(context: Context): PersonasDb {
            return instance ?: synchronized(this) {
                instance ?: buildDataBase(context).also { instance = it }
            }
        }
    }*/

}