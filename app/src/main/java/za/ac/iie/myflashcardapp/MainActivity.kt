package za.ac.iie.myflashcardapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Logging tag for debugging
    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "Activity created")

        // Set up edge-to-edge display
        enableEdgeToEdge()

        // Initialize UI components
        val startButton: Button = findViewById(R.id.button)

        // Set click listener for start button
        startButton.setOnClickListener {
            Log.d(TAG, "Start button clicked - launching quiz")
            startActivity(Intent(this, FlashcardQuestionActivity::class.java))
        }
    }

    // Activity lifecycle methods with logging
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "Activity becoming visible")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "Activity no longer visible")
    }
}
