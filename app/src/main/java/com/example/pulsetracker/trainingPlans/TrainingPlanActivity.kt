package com.example.pulsetracker.trainingPlans

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.pulsetracker.R
import com.example.pulsetracker.adapter.ModeAdapter
import com.example.pulsetracker.data.Mode
import com.example.pulsetracker.enums.TrainingType


abstract class TrainingPlanActivity(private val trainingType: TrainingType) : AppCompatActivity() {

    protected lateinit var descriptionTextView: TextView
    protected lateinit var startButton: Button
    private lateinit var returnButton: Button

    abstract val trainingActivityClass: Class<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_plan)

        descriptionTextView = findViewById(R.id.training_description)
        startButton = findViewById(R.id.start_training_button)
        returnButton = findViewById(R.id.return_button)

        returnButton.setOnClickListener {
            finish() // This will close the current activity and go back to MainActivity
        }
    }
}
