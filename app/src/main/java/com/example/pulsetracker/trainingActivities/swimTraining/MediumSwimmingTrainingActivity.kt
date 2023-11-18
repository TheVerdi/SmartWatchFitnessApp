package com.example.pulsetracker.trainingActivities.swimTraining

import com.example.pulsetracker.constants.AppTexts.COOL_DOWN_SWIM
import com.example.pulsetracker.constants.AppTexts.MODERATE_PACE_SWIM
import com.example.pulsetracker.constants.AppTexts.REST
import com.example.pulsetracker.constants.AppTexts.WARM_UP_SWIM
import com.example.pulsetracker.utils.AbsTrainingActivity
import com.example.pulsetracker.utils.TrainingUtils

class MediumSwimmingTrainingActivity : AbsTrainingActivity("Medium", "Swimming") {
    override val trainingPlan = TrainingUtils.generateTrainingSteps(
        WARM_UP_SWIM,
        5 * 60 * 1000,
        MODERATE_PACE_SWIM,
        5 * 60 * 1000,
        REST,
        2 * 60 * 1000,
        15,
        COOL_DOWN_SWIM,
        5 * 60 * 1000
    )
}