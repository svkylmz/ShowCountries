package com.svkylmz.showcountries.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Country (
    @ColumnInfo(name = "name")       // Room
    @SerializedName("name")    // Retrofit
    val countryName: String?,

    @ColumnInfo(name = "region")
    @SerializedName("region")
    val countryRegion: String?,

    @ColumnInfo(name = "capital")
    @SerializedName("capital")
    val countryCapital: String?,

    @ColumnInfo(name = "currency")
    @SerializedName("currency")
    val countryCurrency: String?,

    @ColumnInfo(name = "language")
    @SerializedName("language")
    val countryLanguage: String?,

    @ColumnInfo(name = "flag")
    @SerializedName("flag")
    val countryFlagImageUrl: String?
    ) {
    @PrimaryKey(autoGenerate = true)
    val uuid: Int = 0
}