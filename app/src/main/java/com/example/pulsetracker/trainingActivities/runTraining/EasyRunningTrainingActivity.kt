package com.example.pulsetracker.trainingActivities.runTraining

import com.example.pulsetracker.constants.AppTexts.COOL_DOWN_WALK
import com.example.pulsetracker.constants.AppTexts.REST
import com.example.pulsetracker.constants.AppTexts.RUN
import com.example.pulsetracker.constants.AppTexts.WARM_UP_WALK
import com.example.pulsetracker.utils.AbsTrainingActivity
import com.example.pulsetracker.utils.TrainingUtils

class EasyRunningTrainingActivity : AbsTrainingActivity("Easy", "Running") {
    override val trainingPlan = TrainingUtils.generateTrainingSteps(
        // repeat count - amount of seconds - 1 second
        WARM_UP_WALK,
        1 * 60 * 10,
        RUN,
        1 * 60 * 10,
        REST,
        1 * 60 * 10,
        1,
        COOL_DOWN_WALK,
        1 * 60 * 10
    )
}
