package com.nexle.example_app.utils

object CommonUtils {
    fun isValidSpecial(s: String): Boolean {
        var isLetterOrDigit: Boolean
        for (element in s) {
            isLetterOrDigit = Character.isLetterOrDigit(element)
            if (!isLetterOrDigit) {
                return true
            }
        }
        return false
    }
}