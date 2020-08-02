package com.aqube.coroutineexample.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.aqube.coroutineexample.data.LoginState
import com.aqube.coroutineexample.data.UserDatabase
import kotlinx.coroutines.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val coroutineScope = CoroutineScope(Dispatchers.IO)
    var job: Job? = null
    val db by lazy { UserDatabase(application).userDao() }
    val userDeleted = MutableLiveData<Boolean>()
    val logout = MutableLiveData<Boolean>()

    fun onLogout() {
        LoginState.logout()
        logout.value = true
    }

    fun onDeleteUser() {
        coroutineScope.launch {
            LoginState.user?.let {
                db.deleteUser(it.id)
            }
            withContext(Dispatchers.Main) {
                LoginState.logout()
                userDeleted.value = true
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}