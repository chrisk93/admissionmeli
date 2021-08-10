package com.example.mercadolibreprueba.api

import com.example.mercadolibreprueba.model.search.Base
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Service {

    @GET("sites/{site}/search")
    suspend fun getSearchItems(
        @Path("site") site: String = "MLA",
        @Query("q") query : String
    ) : Base

}