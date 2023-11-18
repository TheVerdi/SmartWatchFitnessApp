package com.example.pulsetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.pulsetracker.database.UserRepository

class CongratulationsActivity : AppCompatActivity() {
    private lateinit var returnButton: Button
    private lateinit var congratulationsMessage: TextView
    private val userRepository = UserRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_congratulations)

        returnButton = findViewById(R.id.return_button)
        congratulationsMessage = findViewById(R.id.congratulations_message)

        val trainingType = intent.getStringExtra("TRAINING_TYPE")
        val trainingMode = intent.getStringExtra("TRAINING_MODE")

        congratulationsMessage.text =
            "Congratulations! You've completed your $trainingType training on $trainingMode mode."


        returnButton.setOnClickListener {
            userRepository.saveUserTrainingToDB(
                trainingType.toString(),
                trainingMode.toString()
            )
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
