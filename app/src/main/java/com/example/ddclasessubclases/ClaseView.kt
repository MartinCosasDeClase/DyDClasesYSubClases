package com.example.ddclasessubclases

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import java.util.concurrent.Executors

class ClaseView(private val app: Application) : AndroidViewModel(app) {
    private val appDatabase: AppDatabase = AppDatabase.getDatabase(
        this.getApplication()
    )
    private val claseDao: ClaseDAO = appDatabase.claseDao
    val clases: LiveData<List<Clase>>
        get() = claseDao.getClases()
    private val subClaseDao: SubClaseDAO = appDatabase.subClaseDao
    val subClase:LiveData<List<SubClase>>
        get() = subClaseDao.getSubClases()

     suspend fun reload() {
         val executors = Executors.newSingleThreadExecutor()
         val clase = getClases()
         val subClase = getSubClases()
         executors.execute {
             claseDao.deleteClases()
             claseDao.addClases(clase)
             subClaseDao.deleteSubClases()
             subClaseDao.addSubClases(subClase)
         }

    }

    private suspend fun getSubClases(): ArrayList<SubClase> {
        val subClases = RetrofitServiceFactory.makeRetrofitService()
            .listaSubClases("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImV5cmRrdnZyaGJjaWJubXljZnp3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzE2NjEwOTgsImV4cCI6MjA0NzIzNzA5OH0.pwMOzVqg5eDMn0ywTowo4HEakJCFFFozRiKJVKphpV4")
        Log.d("XXX", subClases.toString())
        return subClases
    }

    suspend fun getClases():ArrayList<Clase>{
        return RetrofitServiceFactory.makeRetrofitService()
            .listCLases("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImV5cmRrdnZyaGJjaWJubXljZnp3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzE2NjEwOTgsImV4cCI6MjA0NzIzNzA5OH0.pwMOzVqg5eDMn0ywTowo4HEakJCFFFozRiKJVKphpV4")
    }
}