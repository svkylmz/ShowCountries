package com.svkylmz.showcountries.service

import com.svkylmz.showcountries.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {
    //GET POST UPDATE DELETE
    //https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    //BASE_URL : https://raw.githubusercontent.com/
    //EXTENSION : atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries(): Single<List<Country>> //does not matter using single or observable for small datas
}