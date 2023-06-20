package com.example.pulsetracker

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.pulsetracker.adapter.ModeAdapter
import com.example.pulsetracker.data.Mode
import com.example.pulsetracker.enums.TrainingType
import com.example.pulsetracker.utils.TrainingFactory

class TrainingActivity : AppCompatActivity() {
    private lateinit var modesViewPager: ViewPager
    private var trainingType: TrainingType = TrainingType.RUNNING  // default to running

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training)

        // Retrieve the training type from Intent
        trainingType = intent.getSerializableExtra("TRAINING_TYPE") as? TrainingType ?: TrainingType.RUNNING

        val modes = listOf(
            Mode("Easy") {
                startActivity(TrainingFactory.createTrainingPlan(this@TrainingActivity, trainingType, "Easy"))
            },
            Mode("Medium") {
                startActivity(TrainingFactory.createTrainingPlan(this@TrainingActivity, trainingType, "Medium"))
            },
            Mode("Hard") {
                startActivity(TrainingFactory.createTrainingPlan(this@TrainingActivity, trainingType, "Hard"))
            }

        )

        val modeAdapter = ModeAdapter(modes, this)

        modesViewPager = findViewById(R.id.modes_view_pager)
        modesViewPager.adapter = modeAdapter

        // Set back button action
        val backButton: Button = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish()  // This will close the current activity and go back to MainActivity
        }
    }
}
