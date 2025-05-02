package za.ac.iie.myflashcardapp

import android.content.Intent
import android.os.Bundle
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

        findViewById<TextView>(R.id.textView5).text = "Score: $score/${questions.size}"
        findViewById<TextView>(R.id.textView6).text =
            if (score >= questions.size / 2) "Great job!" else "Keep practicing!"

        findViewById<Button>(R.id.button5).setOnClickListener {
            val reviewIntent = Intent(this, ReviewActivity::class.java)
            reviewIntent.putStringArrayListExtra("QUESTIONS", questions)
            reviewIntent.putExtra("USER_ANSWERS", userAnswers)
            startActivity(reviewIntent)
        }

        findViewById<Button>(R.id.button6).setOnClickListener {
            finishAffinity()
        }
    }
}
