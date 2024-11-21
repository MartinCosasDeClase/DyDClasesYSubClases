package com.example.ddclasessubclases

import com.fasterxml.jackson.core.JsonFactory
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("Clases")
    suspend fun listCLases(
        @Query("apikey") apikey:String,
        @Query("select") select:String = "*",
    ): ArrayList<Clase>
}
object RetrofitServiceFactory{
    fun makeRetrofitService(): RetrofitService{
        return Retrofit.Builder()
            .baseUrl("https://eyrdkvvrhbcibnmycfzw.supabase.co/rest/v1/")
            .addConverterFactory(JacksonConverterFactory.create())
            .build().create(RetrofitService::class.java)

    }
}