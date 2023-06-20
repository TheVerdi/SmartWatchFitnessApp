package com.example.pulsetracker.trainingPlans

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pulsetracker.MainActivity
import com.example.pulsetracker.R
import com.example.pulsetracker.trainingActivities.EasyTrainingActivity

class EasyTrainingPlanActivity : AppCompatActivity() {

    private lateinit var descriptionTextView: TextView
    private lateinit var startButton: Button
    private lateinit var returnButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy_training_plan)

        descriptionTextView = findViewById(R.id.easy_training_description)
        startButton = findViewById(R.id.start_training_button)
        returnButton = findViewById(R.id.return_button)

        val exerciseDescription = """
            1. Warm up by walking for 5 minutes.
            2. Run at a comfortable pace for 1 minute.
            3. Walk for 1 minute.
            4. Repeat steps 2 and 3 for a total of 20 minutes.
            5. Cool down by walking for 5 minutes.
        """.trimIndent()

        descriptionTextView.text = exerciseDescription

        // Set up the button actions
        startButton.setOnClickListener {
            val intent = Intent(this, EasyTrainingActivity::class.java)
            startActivity(intent)
        }
        returnButton.setOnClickListener {
            finish() // This will close the current activity and go back to TrainingActivity
        }
    }
}
