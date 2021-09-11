package com.svkylmz.showcountries.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.svkylmz.showcountries.model.Country
import com.svkylmz.showcountries.service.CountryDatabase
import kotlinx.coroutines.launch

class DetailViewModel(application: Application): BaseViewModel(application) {
    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoomDatabase(uuid: Int) {
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            val country = dao.getCountry(uuid)
            countryLiveData.value = country
        }
    }
}