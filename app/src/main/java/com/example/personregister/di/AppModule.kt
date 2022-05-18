package com.example.personregister.di

import android.content.Context
import androidx.room.Room
import com.example.personregister.data.OcupacionDao
import com.example.personregister.data.PersonaDao
import com.example.personregister.data.PersonasDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun ProvideTicketDb(@ApplicationContext context: Context): PersonasDb {
        val DATABASE_NAME = "PersonasDb"
        return Room.databaseBuilder(
            context,
            PersonasDb::class.java,
            DATABASE_NAME       )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun ProvidePersonaDAO(personasDb: PersonasDb): PersonaDao {
        return personasDb.personaDao
    }

    @Provides
    fun ProvideOcupacionDAO(personasDb: PersonasDb): OcupacionDao {
        return personasDb.ocupacionDao
    }
}