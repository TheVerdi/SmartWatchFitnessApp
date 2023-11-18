package com.example.pulsetracker.data

data class UserStats(
    val trainingTypes: MutableSet<String> = mutableSetOf(),
    val trainingModes: MutableSet<String> = mutableSetOf()
) {
    fun addTraining(trainingType: String, trainingMode: String) {
        trainingTypes.add(trainingType)
        trainingModes.add(trainingMode)
    }
}

