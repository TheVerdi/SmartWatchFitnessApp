package com.example.pulsetracker.trainingPlans

import android.content.Intent
import android.os.Bundle
import com.example.pulsetracker.enums.TrainingType
import com.example.pulsetracker.trainingActivities.EasyRunningTrainingActivity

class EasyRunningTrainingPlanActivity : TrainingPlanActivity(TrainingType.RUNNING) {

    override val trainingActivityClass = EasyRunningTrainingActivity::class.java

    private val exerciseDescription = """
        1. Warm up by walking for 5 minutes.
        2. Run at a comfortable pace for 1 minute.
        3. Walk for 1 minute.
        4. Repeat steps 2 and 3 for a total of 20 minutes.
        5. Cool down by walking for 5 minutes.
    """.trimIndent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        descriptionTextView.text = exerciseDescription
        startButton.setOnClickListener {
            val intent = Intent(this, trainingActivityClass)
            startActivity(intent)
        }
    }
}
