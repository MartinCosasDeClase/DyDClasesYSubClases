package com.example.ddclasessubclases

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ClaseDAO {
    @Query("select * from clase")
    fun getClases(): LiveData<List<Clase>>

    @Insert
    fun addClase(clase: Clase)

    @Insert
    fun addClases(clases:List<Clase>)

    @Delete
    fun deleteclase(clase: Clase)

    @Query("DELETE from clases")
    fun deleteClases()
}