package com.example.pulsetracker.trainingActivities

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pulsetracker.R
import com.example.pulsetracker.data.TrainingStep
import java.util.Locale

class EasyTrainingActivity : AppCompatActivity() {

    private var currentStepIndex = 0

    private lateinit var timer: CountDownTimer
    private lateinit var stepDescription: TextView
    private lateinit var chronometer: Chronometer
    private lateinit var returnButton: Button

    private val easyTrainingPlan = listOf(
        TrainingStep("Warm up walk", 1 * 60 * 1000),
        TrainingStep("Run", 1 * 60 * 1000)
        //...add more steps as needed
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy_training)

        stepDescription = findViewById(R.id.step_description)
        chronometer = findViewById(R.id.timer)
        returnButton = findViewById(R.id.return_button)

        startStep(currentStepIndex)

        returnButton.setOnClickListener {
            timer.cancel()
            finish()
        }
    }

    private fun startStep(stepIndex: Int) {
        if (stepIndex >= easyTrainingPlan.size) {
            // Training plan is finished
            finish()
            return
        }

        val step = easyTrainingPlan[stepIndex]

        stepDescription.text = step.step

        timer = object : CountDownTimer(step.durationInSeconds.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = (millisUntilFinished / 1000) % 60
                val minutes = (millisUntilFinished / (1000 * 60)) % 60
                chronometer.text = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                currentStepIndex++
                startStep(currentStepIndex)
            }
        }.start()

    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}
