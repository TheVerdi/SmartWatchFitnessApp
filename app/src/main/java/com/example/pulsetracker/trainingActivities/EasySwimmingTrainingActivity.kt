package com.example.pulsetracker.trainingActivities

import com.example.pulsetracker.constants.AppTexts.COOL_DOWN_SWIM
import com.example.pulsetracker.constants.AppTexts.REST
import com.example.pulsetracker.constants.AppTexts.SLOW_PACE_SWIM
import com.example.pulsetracker.constants.AppTexts.WARM_UP_SWIM
import com.example.pulsetracker.utils.AbsTrainingActivity
import com.example.pulsetracker.utils.TrainingUtils

class EasySwimmingTrainingActivity : AbsTrainingActivity() {
    override val trainingPlan = TrainingUtils.generateTrainingSteps(
        WARM_UP_SWIM,
        5 * 60 * 1000,
        SLOW_PACE_SWIM,
        2 * 60 * 1000,
        REST,
        1 * 60 * 1000,
        20,
        COOL_DOWN_SWIM,
        5 * 60 * 1000
    )
}