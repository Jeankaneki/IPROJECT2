package za.ac.iie.myflashcardapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Companion object to hold constant values
    companion object {
        private const val TAG = "MainActivity"  // Tag for logging
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge display (makes app full screen)
        enableEdgeToEdge()

        // Set the main layout file for this activity
        setContentView(R.layout.activity_main)

        // Log when activity is created
        Log.d(TAG, "Activity created")

        // Find the start button from layout using its ID
        val startButton: Button = findViewById(R.id.button)

        // Set click listener for the start button
        startButton.setOnClickListener {
            // Log button click event
            Log.d(TAG, "Start button clicked")

            // Create explicit intent to start FlashcardQuestionActivity
            val intent = Intent(this, FlashcardQuestionActivity::class.java)

            // Log before starting new activity
            Log.i(TAG, "Starting FlashcardQuestionActivity")

            // Launch the flashcard questions activity
            startActivity(intent)
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
