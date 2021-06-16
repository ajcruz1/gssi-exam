package com.example.countriesapplication.main.model

data class Country(
    var name: String?,
    var capital: String?,
    var region: String?,
    var alpha2Code: String?,
    var callingCodes: List<String>?,
    var population: Integer?,
    var currencies: List<Currency>?,
    var latlng: List<Double>?,
    var languages: List<Language>?,
    var borders: List<String>?,
    var flag: String?
)
