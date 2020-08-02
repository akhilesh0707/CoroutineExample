package com.aqube.coroutineexample

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

/**
 * Flow cannot be cancel by itself
 * It will be canceled when encompassing coroutine is cancelled
 */
fun main() {
    runBlocking {
        val sendNumbers = sendNum()
        println("Flow not started emitting the data")
        println("Lets start flow")
        //Cancelling
        withTimeoutOrNull(1000L) {
            sendNumbers.collect { println(it) }
        }
    }
}

fun sendNum() = flow {
    listOf(1, 2, 3, 4).forEach {
        delay(400L)
        emit(it)
    }
}
