package com.example.countriesapplication.network

import com.example.countriesapplication.main.model.Country
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("all?fields=name;capital;region;alpha2Code;callingCodes;population;currencies;latlng;languages;borders;flag")
    fun getAll(): Call<List<Country>>

    @GET("name/{name}")
    fun getCountryByName(@Path("name") name: String): Call<List<Country>>
}