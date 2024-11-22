package com.example.ddclasessubclases

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import java.util.concurrent.Executors

class ClaseView(private val app: Application) : AndroidViewModel(app) {
    private val appDatabase: AppDatabase = AppDatabase.getDatabase(
        this.getApplication()
    )
    private val pokemonDao: ClaseDAO = appDatabase.pokemonDao
    val pokemons: LiveData<List<Clase>>
        get() = pokemonDao.getClases()


    fun reload() {
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {

            val retrofit = RetrofitServiceFactory.makeRetrofitService()
            val clases = retrofit.listCLases()
            pokemonDao.deleteClases()
            pokemonDao.addClases(clases)
        }
    }

}