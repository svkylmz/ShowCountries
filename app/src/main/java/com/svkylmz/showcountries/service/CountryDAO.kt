package com.svkylmz.showcountries.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.svkylmz.showcountries.model.Country

@Dao // Data Access Object
interface CountryDAO {
    @Insert // -> INSERT INTO sql query
    suspend fun insertAll(vararg countries: Country): List<Long>
    // suspend -> coroutine - pause & resume operations
    // vararg -> multiple country objects
    // List<Long> -> primary keys

    @Query("SELECT * FROM country")
    suspend fun getAllCountries(): List<Country>

    @Query("SELECT * FROM country WHERE uuid = :countryId")
    suspend fun getCountry(countryId: Int): Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()
}