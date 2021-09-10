package com.svkylmz.showcountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.svkylmz.showcountries.model.Country

class FeedViewModel: ViewModel() {
    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData() {
        val country1 = Country(
            "TURKEY",
            "Asia",
            "Ankara",
            "TRY",
            "Turkish",
            "www.ss.com")
        val country2 = Country(
            "FRANCE",
            "Europe",
            "Paris",
            "EUR",
            "French",
            "www.ss.com")
        val country3 = Country(
            "GERMANY",
            "Europe",
            "Berlin",
            "EUR",
            "German",
            "www.ss.com")

        val countryList = arrayListOf<Country>(country1, country2, country3)

        countries.value = countryList
        countryError.value = false
        countryLoading.value = false
    }
}