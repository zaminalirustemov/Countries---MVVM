package com.asparagas.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asparagas.countries.model.Country

class CountryViewModel : ViewModel() {

    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(){
        val country = Country("Azerbaijan", "Baku", "Asia", "AZN", "www.imageurl", "Azerbaijan")
        countryLiveData.value=country
    }
}