package com.example.pulsetracker.trainingActivities

import com.example.pulsetracker.data.TrainingStep

class MediumTrainingActivity : TrainingActivity() {
    override val trainingPlan = listOf(
        TrainingStep("Warm up walk", 5 * 60 * 1000),
        TrainingStep("Run", 5 * 60 * 1000),
        TrainingStep("Walk", 2 * 60 * 1000),
        TrainingStep("Run", 5 * 60 * 1000),  // Repeat as many times as necessary
        TrainingStep("Cool down walk", 5 * 60 * 1000)
    )

}