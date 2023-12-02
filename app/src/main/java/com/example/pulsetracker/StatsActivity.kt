package com.example.pulsetracker

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pulsetracker.database.UserRepository
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.auth.FirebaseAuth

class StatsActivity : AppCompatActivity() {
    private lateinit var userStatsRepository: UserRepository
    private lateinit var textViewStats: TextView
    private lateinit var pieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        userStatsRepository = UserRepository()

        textViewStats = findViewById(R.id.textViewStats)

        pieChart = findViewById(R.id.pieChart)

        val userId = FirebaseAuth.getInstance().currentUser?.uid

        userId?.let {
            getUserStats(it)
        }

        val btnReturnToMain: Button = findViewById(R.id.btnReturnToMain)
        btnReturnToMain.setOnClickListener {
            finish()
        }
    }

    private fun getUserStats(userId: String) {
        userStatsRepository.getUserStats(userId) { userStats ->
// Inside the getUserStats callback
            runOnUiThread {
                textViewStats.text =
                    "\nTotal Trainings: ${userStats.totalTrainings}\n" +
                            "Last Training Type: ${userStats.lastTrainingType}\n" +
                            "Last Training Mode: ${userStats.lastTrainingMode}\n"

                setupPieChart(pieChart, userStats.trainingTypeCounts)

            }

        }
    }

    private fun setupPieChart(pieChart: PieChart, trainingTypeCounts: Map<String, Int>) {
        val entries: ArrayList<PieEntry> = ArrayList()

        for ((type, count) in trainingTypeCounts) {
            entries.add(PieEntry(count.toFloat(), type))
        }

        val colors = ArrayList<Int>()
        for (color in ColorTemplate.COLORFUL_COLORS) {
            colors.add(color)
        }

        val dataSet = PieDataSet(entries, "")
        dataSet.colors = colors
        dataSet.valueTextSize = 8f
        dataSet.setDrawValues(false)

        val data = PieData(dataSet)
        pieChart.data = data
        pieChart.description.isEnabled = false
        pieChart.centerText = ""
        pieChart.setEntryLabelTextSize(8f)
        pieChart.animateY(1000)
    }
}




