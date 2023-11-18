package com.example.pulsetracker.trainingActivities.swimTraining

import com.example.pulsetracker.constants.AppTexts.COOL_DOWN_SWIM
import com.example.pulsetracker.constants.AppTexts.FAST_PACE_SWIM
import com.example.pulsetracker.constants.AppTexts.REST
import com.example.pulsetracker.constants.AppTexts.WARM_UP_SWIM
import com.example.pulsetracker.utils.AbsTrainingActivity
import com.example.pulsetracker.utils.TrainingUtils

class HardSwimmingTrainingActivity : AbsTrainingActivity("Hard", "Swimming") {
    override val trainingPlan = TrainingUtils.generateTrainingSteps(
        WARM_UP_SWIM,
        5 * 60 * 1000,
        FAST_PACE_SWIM,
        10 * 60 * 1000,
        REST,
        2 * 60 * 1000,
        12,
        COOL_DOWN_SWIM,
        5 * 60 * 1000
    )
}