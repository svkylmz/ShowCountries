package com.svkylmz.showcountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.svkylmz.showcountries.model.Country

class DetailViewModel: ViewModel() {
    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoomDatabase() {
        val country = Country(
            "TURKEY",
            "Asia",
            "Ankara",
            "TRY",
            "Turkish",
            "www.ss.com")
        countryLiveData.value = country
    }
}