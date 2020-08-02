package com.aqube.coroutineexample

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        getPrimeFlow().collect {
            println("Receive: $it")
        }
    }
}

fun getPrimeFlow(): Flow<Int> = flow {
    val primeNumList = listOf(2, 3, 5, 7, 11, 13, 19, 23, 29)
    primeNumList.forEach {
        emit(it)
    }
}

/**
 * Collection can be converted directly to flow
 */
fun getCollectionConvertFlow() = listOf(1, 2, 3, 4, 5).asFlow()

/**
 * A flow can be generated from number of parameters of any type
 */
fun getFlowOf() = flowOf("One", "Two", "Three", "Four", "Five")

