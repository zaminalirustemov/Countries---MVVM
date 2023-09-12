package com.asparagas.countries.service

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.asparagas.countries.model.Country

@Dao
interface CountryDao {
    @Insert
    fun insertAll(vararg countries: Country): List<Long>

    @Query("SELECT * FROM country")
    fun getAllCountries(): List<Country>

    @Query("SELECT * FROM country WHERE uuid = :countryId")
    fun getCountry(countryId: Int): Country

    @Query("DELETE FROM country")
    fun deleteAllCountries()
}