package za.ac.iie.myflashcardapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val score = intent.getIntExtra("SCORE", 0)
        val questions = intent.getStringArrayListExtra("QUESTIONS") ?: arrayListOf()
        val userAnswers = intent.getBooleanArrayExtra("USER_ANSWERS") ?: booleanArrayOf()

        Log.d("ScoreActivity", "Received score: $score, Total questions: ${questions.size}")
        Log.d("ScoreActivity", "User answers size: ${userAnswers.size}")

        findViewById<TextView>(R.id.textView5).text = "Score: $score/${questions.size}"
        // feedback message logic

        if (score >= 3) {
            findViewById<TextView>(R.id.textView6).text = "Great job!"  // Good score
        } else {
            findViewById<TextView>(R.id.textView6).text = "Keep practicing!"  // Needs improvement
        }

        findViewById<Button>(R.id.button5).setOnClickListener {
            val reviewIntent = Intent(this, ReviewActivity::class.java)
            reviewIntent.putStringArrayListExtra("QUESTIONS", questions)
            reviewIntent.putExtra("USER_ANSWERS", userAnswers)
            reviewIntent.putExtra("CORRECT_ANSWERS", intent.getBooleanArrayExtra("CORRECT_ANSWERS"))  // Add this line
            startActivity(reviewIntent)
        }

        findViewById<Button>(R.id.button6).setOnClickListener {
            Log.d("ScoreActivity", "Exit button clicked")
            finishAffinity()
        }
    }
}