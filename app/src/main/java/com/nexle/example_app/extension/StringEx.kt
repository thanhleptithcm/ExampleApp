package com.nexle.example_app.extension

import android.util.Patterns

fun String?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
fun String.isDigit() = any { it.isDigit() }
fun String.isUppercase() = any { it.isUpperCase() }
