package com.example.pulsetracker.trainingPlans.swimPlan

import android.content.Intent
import android.os.Bundle
import com.example.pulsetracker.enums.TrainingType
import com.example.pulsetracker.trainingActivities.swimTraining.MediumSwimmingTrainingActivity
import com.example.pulsetracker.trainingPlans.TrainingPlanActivity

class MediumSwimmingTrainingPlanActivity : TrainingPlanActivity(TrainingType.CYCLING) {

    override val trainingActivityClass = MediumSwimmingTrainingActivity::class.java

    private val exerciseDescription = """
    1. Warm up by swimming at a comfortable pace for 5 minutes.
    2. Swim at a moderate pace for 5 minutes.
    3. Rest for 2 minutes.
    4. Repeat steps 2 and 3 for a total of 30 minutes.
    5. Cool down by swimming slowly for 5 minutes.
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
