package com.example.pulsetracker.database

import android.util.Log
import com.example.pulsetracker.constants.AppTexts.NODE_USER_TRAINING
import com.example.pulsetracker.constants.AppTexts.REPOSITORY_REFERENCE
import com.example.pulsetracker.data.UserStats
import com.example.pulsetracker.data.UserTraining
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserRepository {

    private var databaseRef: DatabaseReference

    init {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        databaseRef = FirebaseDatabase
            .getInstance(REPOSITORY_REFERENCE)
            .getReference(NODE_USER_TRAINING).child(userId.orEmpty())
    }

    fun saveUserTrainingToDB(trainingType: String, trainingMode: String) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid

        userId?.let {

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

    fun getUserStats(userId: String, callback: (UserStats) -> Unit) {
        databaseRef.child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userStats = UserStats()

                for (trainingSnapshot in snapshot.children) {
                    val training = trainingSnapshot.getValue(UserTraining::class.java)
                    if (training != null) {
                        userStats.addTraining(
                            training.trainingType ?: "",
                            training.trainingMode ?: ""
                        )
                    }
                }

                callback.invoke(userStats)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle the error
            }
        })
    }
}