package com.example.pulsetracker.data

data class Mode(
    val name: String,
    val onClick: () -> Unit  // This is the new property
)
