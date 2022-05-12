package com.example.personregister.data

import androidx.room.*
import com.example.personregister.model.Ocupacion
import com.example.personregister.model.Persona
import kotlinx.coroutines.flow.Flow

@Dao
interface OcupacionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertar(ocupacion : Ocupacion)

    @Delete
    suspend fun eliminar(ocupacion : Ocupacion)

    @Query("""
        SELECT * 
        FROM Ocupaciones
        WHERE OcupacionId=:ocupacionId
    """)
    fun buscar(ocupacionId: Int): Flow<Ocupacion>

    @Query("""
        SELECT * 
        FROM Ocupaciones
        ORDER BY OcupacionId    
    """)
    fun getList(): Flow<List<Ocupacion>>
}