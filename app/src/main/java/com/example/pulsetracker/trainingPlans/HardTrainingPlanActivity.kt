package com.example.pulsetracker.trainingPlans

import com.example.pulsetracker.trainingActivities.EasyTrainingActivity

class HardTrainingPlanActivity : TrainingPlanActivity() {
    override val exerciseDescription = """
        1. Warm up by walking for 5 minutes.
        2. Run at a challenging pace for 10 minutes.
        3. Walk for 1 minute.
        4. Repeat steps 2 and 3 for a total of 40 minutes.
        5. Cool down by walking for 5 minutes.
    """.trimIndent()

    override val trainingActivityClass = EasyTrainingActivity::class.java
}