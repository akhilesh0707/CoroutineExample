package com.aqube.coroutineexample.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CountryService {

    private val BASE_URL = "https://raw.githubusercontent.com/"

    fun getCountryService(): CountryApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountryApi::class.java)
    }
}