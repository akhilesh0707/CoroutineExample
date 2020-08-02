package com.aqube.coroutineexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aqube.coroutineexample.network.NewsRepository
import kotlinx.coroutines.*

class ListViewModel : ViewModel() {
    val newArticles = NewsRepository().getNewsArticles().asLiveData()
}