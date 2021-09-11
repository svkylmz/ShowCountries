package com.svkylmz.showcountries.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.svkylmz.showcountries.model.Country
import com.svkylmz.showcountries.service.CountryAPIService
import com.svkylmz.showcountries.service.CountryDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FeedViewModel(application: Application): BaseViewModel(application) {
    private val countryApiService = CountryAPIService()
    private val disposable = CompositeDisposable()

    val countries = MutableLiveData<List<Country>>()
    val countryError = MutableLiveData<Boolean>()
    val countryLoading = MutableLiveData<Boolean>()

    fun refreshData() {
        getDataFromAPI()
    }

    private fun getDataFromAPI() {
        countryLoading.value = true

        disposable.add(
            countryApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                    override fun onSuccess(t: List<Country>) {
                        storeInSqlite(t)
                    }

                    override fun onError(e: Throwable) {
                        countryLoading.value = false
                        countryError.value = true
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun showCountries(countryList: List<Country>) {
        countries.value = countryList
        countryError.value = false
        countryLoading.value = false
    }

    private fun storeInSqlite(list: List<Country>) {
        //background actions without blocking the UI
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries() //first deleting operation
            val listLong = dao.insertAll(*list.toTypedArray())  // list -> individual elements
            var i = 0
            while (i < list.size) {
                list[i].uuid = listLong[i].toInt()
                i += 1
            }
            showCountries(list)
        }
    }
}