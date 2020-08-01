package com.aqube.coroutineexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aqube.coroutineexample.model.Country
import com.aqube.coroutineexample.network.CountryService
import kotlinx.coroutines.*

class ListViewModel : ViewModel() {
    private val countryService = CountryService.getCountryService()
    private var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception: ${throwable.localizedMessage}")
    }
    val countries = MutableLiveData<List<Country>>()
    val error = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = countryService.getCountryList()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    countries.value = response.body()
                    error.value = null
                    loading.value = false
                } else {
                    onError("Error: ${response.errorBody()}")
                }
            }
        }
    }

    private fun onError(message: String) {
        error.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}