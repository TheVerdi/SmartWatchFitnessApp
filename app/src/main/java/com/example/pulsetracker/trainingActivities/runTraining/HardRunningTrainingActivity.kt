package com.example.pulsetracker.trainingActivities.runTraining

import com.example.pulsetracker.constants.AppTexts.COOL_DOWN_WALK
import com.example.pulsetracker.constants.AppTexts.REST
import com.example.pulsetracker.constants.AppTexts.RUN
import com.example.pulsetracker.constants.AppTexts.WARM_UP_WALK
import com.example.pulsetracker.utils.AbsTrainingActivity
import com.example.pulsetracker.utils.TrainingUtils

class HardRunningTrainingActivity : AbsTrainingActivity("Hard", "Running") {
    override val trainingPlan = TrainingUtils.generateTrainingSteps(
        WARM_UP_WALK,
        5 * 60 * 1000,
        RUN,
        10 * 60 * 1000,
        REST,
        1 * 60 * 1000,
        10,
        COOL_DOWN_WALK,
        1 * 60 * 1000
    )
}