package com.example.pulsetracker.data

data class UserStats(
    val totalTrainings: Int,
    val trainingTypeCounts: Map<String, Int>,
    val lastTrainingType: String?,
    val lastTrainingMode: String?
)

