package com.example.pulsetracker.trainingActivities

import com.example.pulsetracker.data.TrainingStep

class HardTrainingActivity : TrainingActivity() {
    override val trainingPlan = listOf(
        TrainingStep("Warm up walk", 5 * 60 * 1000),
        TrainingStep("Run", 10 * 60 * 1000),
        TrainingStep("Walk", 1 * 60 * 1000),
        TrainingStep("Run", 10 * 60 * 1000),  // Repeat as many times as necessary
        TrainingStep("Cool down walk", 5 * 60 * 1000)
    )
}