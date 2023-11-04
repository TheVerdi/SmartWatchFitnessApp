package com.example.pulsetracker.utils

object AuthUtils {
    fun removeEmptySpaces(input: String): String {
        return input.replace("\\s+".toRegex(), "")
    }
}