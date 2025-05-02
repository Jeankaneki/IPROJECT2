package za.ac.iie.myflashcardapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {

    private val TAG = "ScoreActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        Log.d(TAG, "Activity created")

        // Receive quiz results
        val score = intent.getIntExtra("SCORE", 0)
        val totalQuestions = intent.getIntExtra("TOTAL_QUESTIONS", 0)
        val questions = intent.getStringArrayListExtra("QUESTIONS") ?: arrayListOf()
        val userAnswers = intent.getBooleanArrayExtra("USER_ANSWERS") ?: booleanArrayOf()
        val correctAnswers = intent.getBooleanArrayExtra("CORRECT_ANSWERS") ?: booleanArrayOf()

        // Display score
        findViewById<TextView>(R.id.textView5).text = "Score: $score/$totalQuestions"

        // Provide feedback
        findViewById<TextView>(R.id.textView6).text = when {
            score >= 3 -> "Great job!"
            else -> "Keep practicing!"
        }

        // Set up review button
        findViewById<Button>(R.id.button5).setOnClickListener {
            Log.d(TAG, "Review button clicked")
            Intent(this, ReviewActivity::class.java).apply {
                putStringArrayListExtra("QUESTIONS", questions)
                putExtra("USER_ANSWERS", userAnswers)
                putExtra("CORRECT_ANSWERS", correctAnswers)
                startActivity(this)
            }
        }

        // Exit button handler
        findViewById<Button>(R.id.button6).setOnClickListener {
            Log.d(TAG, "Exit button clicked")
            finishAffinity()
        }
    }
}