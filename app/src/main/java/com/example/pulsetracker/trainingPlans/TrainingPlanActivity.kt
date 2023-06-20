package com.example.pulsetracker.trainingPlans

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pulsetracker.R

abstract class TrainingPlanActivity : AppCompatActivity() {

    private lateinit var descriptionTextView: TextView
    private lateinit var startButton: Button
    private lateinit var returnButton: Button

    abstract val exerciseDescription: String
    abstract val trainingActivityClass: Class<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_plan)

        descriptionTextView = findViewById(R.id.training_description)
        startButton = findViewById(R.id.start_training_button)
        returnButton = findViewById(R.id.return_button)

        descriptionTextView.text = exerciseDescription

        // Set up the button actions
        startButton.setOnClickListener {
            val intent = Intent(this, trainingActivityClass)
            startActivity(intent)
        }
        returnButton.setOnClickListener {
            finish() // This will close the current activity and go back to TrainingActivity
        }
    }
}
