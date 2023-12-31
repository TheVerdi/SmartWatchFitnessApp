package com.example.pulsetracker.trainingActivities.cycleTraining

import com.example.pulsetracker.constants.AppTexts.COOL_DOWN_CYCLE
import com.example.pulsetracker.constants.AppTexts.CYCLE
import com.example.pulsetracker.constants.AppTexts.REST
import com.example.pulsetracker.constants.AppTexts.WARM_UP_CYCLE
import com.example.pulsetracker.utils.AbsTrainingActivity
import com.example.pulsetracker.utils.TrainingUtils

class MediumCyclingTrainingActivity : AbsTrainingActivity("Medium", "Cycling") {
    override val trainingPlan = TrainingUtils.generateTrainingSteps(
        WARM_UP_CYCLE,
        5 * 60 * 1000,
        CYCLE,
        10 * 60 * 1000,
        REST,
        1 * 60 * 1000,
        3,
        COOL_DOWN_CYCLE,
        1 * 60 * 1000
    )
}