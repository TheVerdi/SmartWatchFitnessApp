package com.example.pulsetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.pulsetracker.adapter.ModeAdapter
import com.example.pulsetracker.data.Mode
import com.example.pulsetracker.trainingPlans.EasyTrainingPlanActivity
import com.example.pulsetracker.trainingPlans.HardTrainingPlanActivity
import com.example.pulsetracker.trainingPlans.MediumTrainingPlanActivity


class TrainingActivity : AppCompatActivity() {
    private lateinit var modesViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training)

        val modes = listOf(
            Mode("Easy") {
                val intent = Intent(this, EasyTrainingPlanActivity::class.java)
                startActivity(intent)
            },
            Mode("Medium") {
                val intent = Intent(this, MediumTrainingPlanActivity::class.java)
                startActivity(intent)
            },
            Mode("Hard") {
                val intent = Intent(this, HardTrainingPlanActivity::class.java)
                startActivity(intent)
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
