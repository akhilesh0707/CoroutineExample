package com.aqube.coroutineexample.ui.signup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.aqube.coroutineexample.data.LoginState
import com.aqube.coroutineexample.data.User
import com.aqube.coroutineexample.data.UserDatabase
import kotlinx.coroutines.*

class SignupViewModel(application: Application) : AndroidViewModel(application) {

    val coroutineScope = CoroutineScope(Dispatchers.IO)
    var job: Job? = null
    val db by lazy { UserDatabase(application).userDao() }
    val signUpComplete = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun signUp(username: String, password: String, address: String, info: String) {
        job = coroutineScope.launch {
            val user = db.getUser(username)
            if (user != null) {
                withContext(Dispatchers.Main) {
                    error.value = "User already exists"
                }
            } else {
                val user = User(username, password.hashCode(), address, info)
                user.id = db.insertUser(user)
                LoginState.login(user)
                withContext(Dispatchers.Main) {
                    signUpComplete.value = true
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}