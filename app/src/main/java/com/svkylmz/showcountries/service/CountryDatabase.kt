package com.svkylmz.showcountries.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.svkylmz.showcountries.model.Country

@Database(entities = [Country::class], version = 1)
abstract class CountryDatabase: RoomDatabase() {
    abstract fun countryDao(): CountryDAO

    //Singleton is a class from which only one object can be created -> to avoid conflict
    companion object {  //accessible from anywhere
        @Volatile private var instance : CountryDatabase? = null
        // Volatile -> meaning that writes to this field are immediately made visible to other threads.
        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {    // ?: means "if not"
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }

        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CountryDatabase::class.java,
            "countrydatabase"
        ).build()
    }
}