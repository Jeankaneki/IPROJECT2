package za.ac.iie.myflashcardapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        val tvReview: TextView = findViewById(R.id.tvReview)
        val btnExit: Button = findViewById(R.id.btnExit)

        val questions = intent.getStringArrayListExtra("QUESTIONS") ?: arrayListOf()
        val userAnswers = intent.getBooleanArrayExtra("USER_ANSWERS") ?: booleanArrayOf()

        val reviewText = StringBuilder()
        for (i in questions.indices) {
            val answer = userAnswers.getOrNull(i)?.toString() ?: "Not answered"
            reviewText.append("${i + 1}. ${questions[i]}\n")
            reviewText.append("Your answer: $answer\n\n")
        }

        tvReview.text = reviewText.toString()

        btnExit.setOnClickListener {
            finishAffinity()
        }
    }
}

