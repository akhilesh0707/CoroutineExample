package com.aqube.coroutineexample

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

/**
 * Flows are cold did not emit the data until observer collect
 */
fun main() {
    runBlocking {
        val sendNumbers = sendNumbers()
        println("Flow not started emitting the data")
        println("Lets start flow")
        sendNumbers.collect { println(it) }
    }
}

fun sendNumbers() = listOf(1, 2, 3, 4, 5).asFlow()