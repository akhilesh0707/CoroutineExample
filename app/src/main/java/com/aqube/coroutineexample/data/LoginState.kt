package com.aqube.coroutineexample.data

object LoginState {
    var isLoggedIn = false
    var user: User? = null

    fun logout() {
        this.isLoggedIn = false
        this.user = null
    }

    fun login(user: User) {
        this.user = user
        this.isLoggedIn = true
    }

}