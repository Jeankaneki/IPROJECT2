package za.ac.iie.myflashcardapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Find the start button by its ID
        val startButton: Button = findViewById(R.id.button)

        // Set an OnClickListener for the start button
        startButton.setOnClickListener {
            // Create an Intent to start the QuizActivity
            val intent = Intent(this, FlashcardQuestionActivity::class.java)
            // Start the QuizActivity
            startActivity(intent)
        }
    }
}