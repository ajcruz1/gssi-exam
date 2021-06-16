package com.example.countriesapplication.main.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.countriesapplication.main.model.Country
import com.example.countriesapplication.main.model.CountryRepository

class CountryViewModel(application: Application): AndroidViewModel(application) {

    private var countryRepository: CountryRepository? = null
    var countryLiveData: LiveData<List<Country>>? = null

    init {
        countryRepository = CountryRepository()
        countryLiveData = MutableLiveData()
    }

    fun getAllCountries() {
        countryLiveData = countryRepository?.getAllCountries()
    }

    fun getCountryByName(countryName: String) {
        countryLiveData = countryRepository?.getCountryByName(countryName)
    }
}