package com.example.pulsetracker.trainingPlans

import com.example.pulsetracker.trainingActivities.EasyTrainingActivity

class MediumTrainingPlanActivity : TrainingPlanActivity() {
    override val exerciseDescription = """
        1. Warm up by walking for 5 minutes.
        2. Run at a moderately challenging pace for 5 minutes.
        3. Walk for 2 minutes.
        4. Repeat steps 2 and 3 for a total of 30 minutes.
        5. Cool down by walking for 5 minutes.
    """.trimIndent()

    override val trainingActivityClass = EasyTrainingActivity::class.java
}