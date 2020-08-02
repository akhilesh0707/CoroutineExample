package com.aqube.coroutineexample

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        //zip()

        combine()
    }
}

suspend fun zip() {
    val english = flowOf("One", "Two", "Three")
    val hindi = flowOf("Ek", "Do", "Teen")
    english.zip(hindi) { a, b ->
        "$a in hindi is $b"
    }.collect {
        println(it)
    }
}

suspend fun combine() {
    val number = (1..5).asFlow().onEach { delay(300L) }
    val values = flowOf("One", "Two", "Three", "Four", "Five").onEach { delay(400L) }
    number.combine(values) { a, b -> "$a -> $b" }
        .collect { println(it) }
}