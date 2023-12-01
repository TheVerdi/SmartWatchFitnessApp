package com.example.pulsetracker

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pulsetracker.database.UserRepository
import com.google.firebase.auth.FirebaseAuth

class StatsActivity : AppCompatActivity() {
    private lateinit var userStatsRepository: UserRepository
    private lateinit var textViewStats: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        userStatsRepository = UserRepository()

        textViewStats = findViewById(R.id.textViewStats)

        val userId = FirebaseAuth.getInstance().currentUser?.uid

        userId?.let {
            getUserStats(it)
        }

        val btnReturnToMain: Button = findViewById(R.id.btnReturnToMain)
        btnReturnToMain.setOnClickListener {
            finish()
        }
    }

    private fun getUserStats(userId: String) {
        userStatsRepository.getUserStats(userId) { userStats ->
// Inside the getUserStats callback
            runOnUiThread {
                textViewStats.text =
                    "\nTotal Trainings: ${userStats.totalTrainings}\n" +
                            "Last Training Type: ${userStats.lastTrainingType}\n" +
                            "Last Training Mode: ${userStats.lastTrainingMode}\n" +
                            "Training Type Counts:\n" +
                            "   - RUNNING: ${
                                userStats.trainingTypeCounts.getOrDefault(
                                    "Running",
                                    0
                                )
                            }\n" +
                            "   - CYCLING: ${
                                userStats.trainingTypeCounts.getOrDefault(
                                    "Cycling",
                                    0
                                )
                            }\n" +
                            "   - SWIMMING: ${
                                userStats.trainingTypeCounts.getOrDefault(
                                    "Swimming",
                                    0
                                )
                            }"
            }

        }
    }
}
