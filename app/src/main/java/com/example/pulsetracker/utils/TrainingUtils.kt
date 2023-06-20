package com.example.pulsetracker.utils

import com.example.pulsetracker.data.TrainingStep

object TrainingUtils {
    fun generateTrainingSteps(
        warmUpDescription: String,
        warmUpDuration: Long,
        exerciseDescription: String,
        exerciseDuration: Long,
        restDescription: String,
        restDuration: Long,
        repeatCount: Int,
        coolDownDescription: String,
        coolDownDuration: Long
    ): List<TrainingStep> {
        val steps = mutableListOf<TrainingStep>()

        steps.add(TrainingStep(warmUpDescription, warmUpDuration.toInt()))

        repeat(repeatCount) {
            steps.add(TrainingStep(exerciseDescription, exerciseDuration.toInt()))
            steps.add(TrainingStep(restDescription, restDuration.toInt()))
        }

        steps.add(TrainingStep(coolDownDescription, coolDownDuration.toInt()))

        return steps
    }
}
