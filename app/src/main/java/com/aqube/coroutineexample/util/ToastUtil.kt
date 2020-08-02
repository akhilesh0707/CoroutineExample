package com.aqube.coroutineexample.util

import android.content.Context
import android.widget.Toast

fun Context.showToastLong(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.showToastShort(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()