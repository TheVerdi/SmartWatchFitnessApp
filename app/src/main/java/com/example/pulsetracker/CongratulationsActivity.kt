package com.example.pulsetracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CongratulationsActivity : AppCompatActivity() {
    private lateinit var returnButton: Button
    private lateinit var congratulationsMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_congratulations)

        returnButton = findViewById(R.id.return_button)
        congratulationsMessage = findViewById(R.id.congratulations_message)

        congratulationsMessage.text = "Congratulations! You've completed your training."

        returnButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
