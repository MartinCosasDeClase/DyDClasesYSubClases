package com.example.ddclasessubclases

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SubClaseDAO {
    @Query("select * from subClase")
    fun getSubClases(): LiveData<List<SubClase>>

    @Insert
    fun addSubClase(subClase: SubClase)

    @Insert
    fun addSubClases(subClase: List<SubClase>)

    @Delete
    fun deleteSubClase(subClase: SubClase)

    @Query("DELETE from subClase")
    fun deleteSubClases()
}