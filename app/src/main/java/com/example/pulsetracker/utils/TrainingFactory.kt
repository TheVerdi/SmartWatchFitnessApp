package com.example.pulsetracker.utils

import android.content.Context
import android.content.Intent
import com.example.pulsetracker.enums.TrainingType
import com.example.pulsetracker.trainingPlans.EasyCyclingTrainingPlanActivity
import com.example.pulsetracker.trainingPlans.EasyRunningTrainingPlanActivity
import com.example.pulsetracker.trainingPlans.EasySwimmingTrainingPlanActivity
import com.example.pulsetracker.trainingPlans.HardCyclingTrainingPlanActivity
import com.example.pulsetracker.trainingPlans.HardRunningTrainingPlanActivity
import com.example.pulsetracker.trainingPlans.HardSwimmingTrainingPlanActivity
import com.example.pulsetracker.trainingPlans.MediumCyclingTrainingPlanActivity
import com.example.pulsetracker.trainingPlans.MediumRunningTrainingPlanActivity
import com.example.pulsetracker.trainingPlans.MediumSwimmingTrainingPlanActivity

object TrainingFactory {
    fun createTrainingPlan(context: Context, trainingType: TrainingType, mode: String): Intent {
        return when (trainingType) {
            TrainingType.RUNNING -> {
                when (mode) {
                    "Easy" -> Intent(context, EasyRunningTrainingPlanActivity::class.java)
                    "Medium" -> Intent(context, MediumRunningTrainingPlanActivity::class.java)
                    "Hard" -> Intent(context, HardRunningTrainingPlanActivity::class.java)
                    else -> throw IllegalArgumentException("Invalid mode for running training plan")
                }
            }
            TrainingType.CYCLING -> {
                when (mode) {
                    "Easy" -> Intent(context, EasyCyclingTrainingPlanActivity::class.java)
                    "Medium" -> Intent(context, MediumCyclingTrainingPlanActivity::class.java)
                    "Hard" -> Intent(context, HardCyclingTrainingPlanActivity::class.java)
                    else -> throw IllegalArgumentException("Invalid mode for cycling training plan")
                }
            }

            TrainingType.SWIMMING -> {
                when (mode) {
                    "Easy" -> Intent(context, EasySwimmingTrainingPlanActivity::class.java)
                    "Medium" -> Intent(context, MediumSwimmingTrainingPlanActivity::class.java)
                    "Hard" -> Intent(context, HardSwimmingTrainingPlanActivity::class.java)
                    else -> throw IllegalArgumentException("Invalid mode for swimming training plan")
                }
            }
        }
    }
}
