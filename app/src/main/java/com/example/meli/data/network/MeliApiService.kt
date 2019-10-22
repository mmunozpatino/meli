package com.example.meli.data.network

import com.example.meli.data.network.responseClasses.ResultProduct
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


//  https://api.mercadolibre.com/sites/MLA/search?q=Motorola%20G6
interface MeliApiService {

    @GET("search")
    fun searchProduct(
        @Query("q") product: String
    ): Deferred<ResultProduct>

    companion object {
        operator fun invoke(): MeliApiService{

            val okHttpClient = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.mercadolibre.com/sites/MLA/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MeliApiService::class.java)
        }
    }

}