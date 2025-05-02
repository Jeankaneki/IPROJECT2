package za.ac.iie.myflashcardapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        // Get references to UI elements
        val textReview: TextView = findViewById(R.id.ReviewText)
        val btnExit: Button = findViewById(R.id.ExitBtn)

        // Get data from previous screen
        val questions = intent.getStringArrayListExtra("QUESTIONS") ?: arrayListOf()
        val userAnswers = intent.getBooleanArrayExtra("USER_ANSWERS") ?: booleanArrayOf()
        val correctAnswers = intent.getBooleanArrayExtra("CORRECT_ANSWERS") ?: booleanArrayOf()

        // Create review text
        val reviewText = StringBuilder()

        // Loop through all questions
        for (i in questions.indices) {
            // Get answers safely
            val userAnswer = userAnswers.getOrNull(i) ?: "Not answered"
            val correctAnswer = correctAnswers.getOrNull(i) ?: false

            // Build review entry for each question
            reviewText.append("Question ${i + 1}: ${questions[i]}\n")
            reviewText.append("Your answer: ${if (userAnswer is Boolean) if (userAnswer) "True" else "False" else userAnswer}\n")

            // Check if answer was correct
            if (userAnswer == correctAnswer) {
                reviewText.append("Result: Correct!\n\n")
            } else {
                reviewText.append("Result: Incorrect (Correct answer was: ${if (correctAnswer) "True" else "False"})\n\n")
            }
        }

        // Display the full review
        textReview.text = reviewText.toString()

        // Exit button click handler
        btnExit.setOnClickListener {
            finishAffinity()  // Close all activities
        }
    }
}

