package com.example.pulsetracker.trainingPlans.runPlan

import android.content.Intent
import android.os.Bundle
import com.example.pulsetracker.enums.TrainingType
import com.example.pulsetracker.trainingActivities.runTraining.MediumRunningTrainingActivity
import com.example.pulsetracker.trainingPlans.TrainingPlanActivity

class MediumRunningTrainingPlanActivity : TrainingPlanActivity(TrainingType.RUNNING) {

    override val trainingActivityClass = MediumRunningTrainingActivity::class.java

    private val exerciseDescription = """
        1. Warm up by walking for 5 minutes.
        2. Run at a moderately challenging pace for 5 minutes.
        3. Walk for 2 minutes.
        4. Repeat steps 2 and 3 for a total of 30 minutes.
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
