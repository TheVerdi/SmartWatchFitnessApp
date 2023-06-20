package com.example.pulsetracker.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pulsetracker.R
import com.example.pulsetracker.data.TrainingStep
import java.util.Locale


class TrainingStepsAdapter(private val trainingSteps: List<TrainingStep>) : RecyclerView.Adapter<TrainingStepsAdapter.ViewHolder>() {

    private val remainingTimes = IntArray(trainingSteps.size) { trainingSteps[it].durationInSeconds }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val stepTextView: TextView = view.findViewById(R.id.training_step_name)
        val timeTextView: TextView = view.findViewById(R.id.training_step_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_training_step, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val trainingStep = trainingSteps[position]
        holder.stepTextView.text = trainingStep.step
        holder.timeTextView.text = formatTime(remainingTimes[position])
    }

    override fun getItemCount() = trainingSteps.size

    private fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, remainingSeconds)
    }

    fun updateTimer(position: Int, remainingSeconds: Int) {
        remainingTimes[position] = remainingSeconds
        notifyItemChanged(position)
    }

    fun getStepDuration(position: Int): Int {
        return trainingSteps[position].durationInSeconds
    }
}
