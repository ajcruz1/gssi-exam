package com.example.countriesapplication.main.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.countriesapplication.network.ApiClient
import com.example.countriesapplication.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryRepository {

    var apiInterface: ApiInterface? = null

    init {
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
    }

    fun getAllCountries(): LiveData<List<Country>> {
        var countries = MutableLiveData<List<Country>>()

        apiInterface?.getAll()?.enqueue(object: Callback<List<Country>>{
            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                countries.value = null
            }

            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                Log.d("response", response.toString())
                if (response.code() == 200 && response.body() != null) {
                    countries.value = response.body()
                } else {
                    countries.value = null
                }
            }
        })

        return countries
    }

    fun getCountryByName(name: String): LiveData<List<Country>> {
        var countries = MutableLiveData<List<Country>>()

        apiInterface?.getCountryByName(name)?.enqueue(object: Callback<List<Country>>{
            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                countries.value = null
            }

            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                if (response.code() == 200 && response.body() != null) {
                    countries.value = response.body()
                } else {
                    countries.value = null
                }
            }
        })

        return countries
    }
}