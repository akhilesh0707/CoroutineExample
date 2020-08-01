package com.aqube.coroutineexample.network

import com.aqube.coroutineexample.model.Country
import retrofit2.Response
import retrofit2.http.GET

interface CountryApi {

    @GET("DevTides/countries/master/countriesV2.json")
    suspend fun getCountryList(): Response<List<Country>>
}