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

//        val userId = FirebaseAuth.getInstance().currentUser?.uid
//        val databaseRef =
//            FirebaseDatabase.getInstance(REPOSITORY_REFERENCE).getReference(NODE_USER_TRAINING).child(userId.orEmpty())

        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var totalTrainings = 0
                var lastTrainingType: String? = null
                var lastTrainingMode: String? = null

                for (childSnapshot in snapshot.children) {
                    val userTraining = childSnapshot.getValue(UserTraining::class.java)

                    userTraining?.let {
                        totalTrainings++
                        lastTrainingType = it.trainingType
                        lastTrainingMode = it.trainingMode
                    }

                }
                val userStats = UserStats(totalTrainings, lastTrainingType, lastTrainingMode)

                // Pass the UserStats object to the callback function
                callback(userStats)


            }

            override fun onCancelled(error: DatabaseError) {
                // Handle errors
                Log.e("Firebase", "Error reading user training data", error.toException())
            }
        })


    }
}