package com.example.pulsetracker

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.DataClient.OnDataChangedListener
import com.google.android.gms.wearable.DataEvent
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataMapItem
import com.google.android.gms.wearable.Wearable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Random
import kotlin.math.roundToInt

class PulsometerActivity : AppCompatActivity(), OnDataChangedListener {

    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var returnButton: Button
    private lateinit var pulseTextView: TextView
    private lateinit var recommendationTextView: TextView
    private lateinit var pulseHandler: Handler
    private lateinit var buttonLayout: LinearLayout
    private var pulseAnimator: AnimatorSet? = null
    private var dataClient: DataClient? = null
    private var isTrackingPulse = false

    companion object {
        private const val REQUEST_PERMISSION_CODE = 1
        private const val PULSE_PATH = "/pulse"
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pulsometer_menu)

        startButton = findViewById(R.id.start_button)
        stopButton = findViewById(R.id.stop_button)
        pulseTextView = findViewById(R.id.pulse_text_view)
        recommendationTextView = findViewById(R.id.recommendation_text_view)
        buttonLayout = findViewById(R.id.button_layout)

        returnButton = Button(this).apply {
            val layoutParams = LinearLayout.LayoutParams(
                32.dpToPx(this@PulsometerActivity), // converting dp to px for consistency
                32.dpToPx(this@PulsometerActivity)
            )
            layoutParams.setMargins(10.dpToPx(this@PulsometerActivity), 0, 0, 0) // add left margin
            this.layoutParams = layoutParams
            background = getDrawable(R.drawable.round_button)

            setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_arrow_left_white, 0, 0, 0)
            visibility = View.GONE
            setPadding(8,0,0,0)

            setOnClickListener {
                finish()
            }
        }
        buttonLayout.addView(returnButton)

        startButton.setOnClickListener { startPulseTracking() }
        stopButton.setOnClickListener { stopPulseTracking() }

        dataClient = Wearable.getDataClient(this)
    }

    private fun startPulseTracking() {
        isTrackingPulse = true
        pulseHandler = Handler()

        val pulseRunnable = object : Runnable {
            override fun run() {
                val random = Random()
                val pulse = random.nextInt(86) + 55
                updatePulseValue(pulse)
                pulseHandler.postDelayed(this, 1000)
            }
        }
        pulseHandler.post(pulseRunnable)
    }

    override fun onResume() {
        super.onResume()
        dataClient?.addListener(this)
    }

    override fun onPause() {
        super.onPause()
        dataClient?.removeListener(this)
    }

    private suspend fun generateRandomPulse(): Int = withContext(Dispatchers.Default) {
        val random = Random()
        return@withContext random.nextInt(86) + 55
    }

    private fun subscribeToPulseData() {
        dataClient?.addListener(this)
        startButton.isEnabled = false
        stopButton.isEnabled = true
    }

    private fun stopPulseTracking() {
        if (!isTrackingPulse) {
            return
        }

        isTrackingPulse = false
        pulseHandler.removeCallbacksAndMessages(null)
        startButton.isEnabled = true
        stopButton.isEnabled = false

        dataClient?.removeListener(this)
        returnButton.visibility = View.VISIBLE
    }

    private fun updatePulseValue(pulse: Int) {
        runOnUiThread {
            val previousPulse = pulseTextView.text.toString().toIntOrNull()
            if (previousPulse != null) {
                animatePulseChange(previousPulse, pulse)
            } else {
                pulseTextView.text = pulse.toString()
            }

            recommendTrainingPlan(pulse)
        }
    }

    private fun animatePulseChange(from: Int, to: Int) {
        pulseAnimator?.cancel()

        val scaleUpAnimator = ObjectAnimator.ofPropertyValuesHolder(
            pulseTextView,
            PropertyValuesHolder.ofFloat(View.SCALE_X, 1.0f, 1.5f),
            PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.0f, 1.5f)
        ).apply {
            duration = 300
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            interpolator = AccelerateDecelerateInterpolator()
        }

        val scaleDownAnimator = ObjectAnimator.ofPropertyValuesHolder(
            pulseTextView,
            PropertyValuesHolder.ofFloat(View.SCALE_X, 1.5f, 1.0f),
            PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.5f, 1.0f)
        ).apply {
            duration = 300
            repeatCount = 1
            repeatMode = ValueAnimator.REVERSE
            interpolator = AccelerateDecelerateInterpolator()
        }

        pulseAnimator = AnimatorSet().apply {
            playSequentially(scaleUpAnimator, scaleDownAnimator)
            start()
        }

        pulseTextView.text = to.toString()
    }

    override fun onDataChanged(dataEvents: DataEventBuffer) {
        for (event in dataEvents) {
            if (event.type == DataEvent.TYPE_CHANGED && event.dataItem.uri.path == PULSE_PATH) {
                val dataMapItem = DataMapItem.fromDataItem(event.dataItem)
                val pulse = dataMapItem.dataMap.getInt("pulse")
                runOnUiThread {
                    pulseTextView.text = pulse.toString()
                    recommendTrainingPlan(pulse)
                }
            }
        }
    }

    private fun recommendTrainingPlan(pulse: Int) {
        val recommendation: String = when {
            pulse < 60 -> "You have a low pulse rate. It's advisable to consult a doctor before starting any training program."
            pulse in 60..99 -> "Your pulse rate is within a healthy range. You can engage in moderate-intensity exercises like brisk walking, swimming, or cycling."
            pulse > 100 -> "Your pulse rate is high. It's recommended to consult a doctor before engaging in any vigorous exercise."
            else -> "Unable to determine recommendation."
        }
        recommendationTextView.text = recommendation
    }

    fun Int.dpToPx(context: Context): Int {
        val density = context.resources.displayMetrics.density
        return (this * density).roundToInt()
    }
}
