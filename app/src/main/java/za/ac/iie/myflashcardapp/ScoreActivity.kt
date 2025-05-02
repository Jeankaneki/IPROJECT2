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
        val questions = intent.getStringArrayListExtra("QUESTIONS")
        val answers = intent.getBooleanArrayExtra("ANSWERS")

        findViewById<TextView>(R.id.textView5).text = "Score: $score/5"
        findViewById<TextView>(R.id.textView6).text =
            if (score >= 3) "Great job!" else "Keep practicing!"

        findViewById<Button>(R.id.button5).setOnClickListener {
            Intent(this, ReviewActivity::class.java).apply {
                putStringArrayListExtra("QUESTIONS", questions)
                putExtra("ANSWERS", answers)
                startActivity(this)
            }
        }

        findViewById<Button>(R.id.button6).setOnClickListener {
            finishAffinity()
        }
    }
}