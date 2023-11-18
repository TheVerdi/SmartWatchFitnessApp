package com.example.pulsetracker

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.pulsetracker.database.UserRepository
import com.google.firebase.auth.FirebaseAuth

class StatsActivity : AppCompatActivity() {
    private lateinit var userStatsRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        userStatsRepository = UserRepository()

        val userId = FirebaseAuth.getInstance().currentUser?.uid

        userId?.let {
            getUserStats(it)
        }

        val btnReturnToMain: Button = findViewById(R.id.btnReturnToMain)
        btnReturnToMain.setOnClickListener {
            finish() // Close the com.example.pulsetracker.StatsActivity and return to the previous activity
        }
    }

    private fun getUserStats(userId: String) {
        userStatsRepository.getUserStats(userId) { userStats ->
            // Now you can use userStats to update UI or perform other operations
            // For example, display the number of different training modes and types
            val numTrainingModes = userStats.trainingModes.size
            val numTrainingTypes = userStats.trainingTypes.size

            // Update UI with the statistics
            // (e.g., set text on TextViews, update charts, etc.)
        }
    }
}
