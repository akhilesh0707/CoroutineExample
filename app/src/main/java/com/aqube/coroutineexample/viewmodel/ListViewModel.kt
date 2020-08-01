package com.aqube.coroutineexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aqube.coroutineexample.model.Country

class ListViewModel : ViewModel() {
    val countries = MutableLiveData<List<Country>>()
    val error = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true
        val dummyData = generateDummyDataCountries()
        countries.value = dummyData
        loading.value = false
    }

    private fun generateDummyDataCountries(): List<Country> {
        val countries = arrayListOf<Country>()
        countries.add(Country("1", "2222", ""))
        countries.add(Country("2", "2222", ""))
        countries.add(Country("3", "2222", ""))
        countries.add(Country("4", "2222", ""))
        return countries
    }

    private fun onError(message: String) {
        error.value = message
        loading.value = false
    }

}