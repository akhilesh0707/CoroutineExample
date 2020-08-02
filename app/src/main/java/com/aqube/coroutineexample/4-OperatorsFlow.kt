package com.aqube.coroutineexample

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

/**
 * Operators take an input flow, transform it and return an modified output flow
 * Operators are cold
 * Returning flow is synchronous
 */
fun main() {
    runBlocking {
        mapOperatorFlow()
    }

}

suspend fun mapOperatorFlow() {
    (1..10).asFlow()
        .map {
            delay(500L)
            "Mapping $it"
        }
        .collect {
            println(it)
        }
}

suspend fun filterOperatorFlow() {
    (1..10).asFlow()
        .filter {
            it % 2 == 0
        }
        .collect {
            println(it)
        }
}

suspend fun transformOperatorFlow() {
    (1..10).asFlow()
        .transform {
            emit("Emitting string value $it")
            emit(it)
        }
        .collect {
            println(it)
        }
}

suspend fun takeOperatorFlow() {
    (1..10).asFlow()
        .take(2)
        .collect { println(it) }
}

suspend fun reduceOperatorFlow() {
    val size = 10
    val factorial = (1..size).asFlow()
        .reduce { accumulator, value ->
            accumulator * value
        }
    println("Factorial of $size is $factorial")
}

suspend fun flowOnOperator() {
    (1..10).asFlow()
        .flowOn(Dispatchers.IO)
        .collect { println(it) }
}