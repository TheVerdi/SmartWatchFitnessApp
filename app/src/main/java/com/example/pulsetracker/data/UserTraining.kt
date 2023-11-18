package com.example.pulsetracker.data

import com.google.firebase.database.Exclude

data class UserTraining(
    @get:Exclude
    var userId: String? = null,
    val userName: String? = null,
    val trainingType: String? = null,
    val trainingMode: String? = null,
    val timestamp: Long? = null
)
