package za.ac.iie.myflashcardapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ReviewActivity : AppCompatActivity() {

    private val TAG = "ReviewActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        Log.d(TAG, "Activity created")

        // Get references to UI elements
        val reviewText: TextView = findViewById(R.id.ReviewText)
        val exitButton: Button = findViewById(R.id.ExitBtn)

        // Receive quiz data
        val questions = intent.getStringArrayListExtra("QUESTIONS") ?: arrayListOf()
        val userAnswers = intent.getBooleanArrayExtra("USER_ANSWERS") ?: booleanArrayOf()
        val correctAnswers = intent.getBooleanArrayExtra("CORRECT_ANSWERS") ?: booleanArrayOf()

        // Build review content
        val reviewContent = buildString {
            questions.forEachIndexed { index, question ->
                append("Question ${index + 1}:\n")
                append("$question\n")
                append("Your answer: ${userAnswers.getOrNull(index)?.let { if (it) "True" else "False" } ?: "Not answered"}\n")
                append("Correct answer: ${correctAnswers.getOrNull(index)?.let { if (it) "True" else "False" } ?: "Unknown"}\n\n")
            }
        }

        // Display review
        reviewText.text = reviewContent

        // Set up exit button
        exitButton.setOnClickListener {
            Log.d(TAG, "Exit button clicked")
            finishAffinity()
        }
    }
}

