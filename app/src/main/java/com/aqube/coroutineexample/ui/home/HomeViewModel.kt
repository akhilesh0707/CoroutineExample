package com.aqube.coroutineexample.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val userDeleted = MutableLiveData<Boolean>()
    val logout = MutableLiveData<Boolean>()

    fun onLogout() {

    }

    fun onDeleteUser() {

    }
}