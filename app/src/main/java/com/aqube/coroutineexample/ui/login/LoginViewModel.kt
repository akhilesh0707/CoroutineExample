package com.aqube.coroutineexample.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.aqube.coroutineexample.data.LoginState
import com.aqube.coroutineexample.data.UserDatabase
import kotlinx.coroutines.*

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val db by lazy { UserDatabase(application).userDao() }
    private var job: Job? = null
    val loginComplete = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun login(username: String, password: String) {
        job = coroutineScope.launch {
            val user = db.getUser(username)
            if (user == null) {
                withContext(Dispatchers.Main) {
                    error.value = "User not found"
                }
            } else {
                if (user.passwordHash == password.hashCode()) {
                    LoginState.login(user)
                    withContext(Dispatchers.Main) {
                        loginComplete.value = true
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        error.value = "Incorrect password"
                    }
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}