package com.example.pulsetracker.trainingPlans

import android.content.Intent
import android.os.Bundle
import com.example.pulsetracker.enums.TrainingType
import com.example.pulsetracker.trainingActivities.MediumCyclingTrainingActivity

class MediumCyclingTrainingPlanActivity : TrainingPlanActivity(TrainingType.CYCLING) {

    override val trainingActivityClass = MediumCyclingTrainingActivity::class.java

    private val exerciseDescription = """
        1. Warm up by cycling at a comfortable pace for 5 minutes.
        2. Cycle at a moderate pace for 10 minutes.
        3. Rest for 2 minutes.
        4. Repeat steps 2 and 3 for a total of 30 minutes.
        5. Cool down by cycling slowly for 5 minutes.
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
