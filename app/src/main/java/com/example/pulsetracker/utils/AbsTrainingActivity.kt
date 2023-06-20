package com.example.pulsetracker.utils

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pulsetracker.CongratulationsActivity
import com.example.pulsetracker.R
import com.example.pulsetracker.data.TrainingStep
import java.util.Locale

abstract class AbsTrainingActivity : AppCompatActivity() {

    var currentStepIndex = 0

    lateinit var timer: CountDownTimer
    lateinit var stepDescription: TextView
    lateinit var chronometer: Chronometer
    lateinit var returnButton: Button

    abstract val trainingPlan: List<TrainingStep>

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

    fun startStep(stepIndex: Int) {
        if (stepIndex >= trainingPlan.size) {
            val intent = Intent(this, CongratulationsActivity::class.java)
            startActivity(intent)
            return
        }

        val step = trainingPlan[stepIndex]

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
