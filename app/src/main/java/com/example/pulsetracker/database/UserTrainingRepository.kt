package com.example.pulsetracker.database

import android.util.Log
import com.example.pulsetracker.constants.AppTexts.NODE_USER_TRAINING
import com.example.pulsetracker.constants.AppTexts.REPOSITORY_REFERENCE
import com.example.pulsetracker.data.UserTraining
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserTrainingRepository {

    private lateinit var databaseRef: DatabaseReference
    fun saveUserTrainingToDB(trainingType: String, trainingMode: String) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid

        userId?.let {
            databaseRef = FirebaseDatabase
                .getInstance(REPOSITORY_REFERENCE)
                .getReference(NODE_USER_TRAINING).child(it)

            val userTrainings = databaseRef.push()

            val userTraining = UserTraining(
                it,
                FirebaseAuth.getInstance().currentUser?.email ?: "",
                trainingType,
                trainingMode,
                System.currentTimeMillis()
            )

            userTrainings.setValue(userTraining)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Data has been written successfully
                        Log.d("Firebase", "Training entry added successfully.")
                    } else {
                        // Handle the error
                        Log.e("Firebase", "Error adding training entry", task.exception)
                    }
                }
        }
    }
}