package za.ac.iie.myflashcardapp

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)


        val questions = intent.getStringArrayListExtra("QUESTIONS")
        val answers = intent.getBooleanArrayExtra("ANSWERS")
        val layout = findViewById<LinearLayout>(R.id.reviewLinearLayout)

        questions?.forEachIndexed { index, question ->
            val answer = answers?.get(index) ?: true
            TextView(this).apply {
                text = buildString {
                    append("${index + 1}. $question")
                    append("\nCorrect Answer: ")
                    append(if (answer) "True" else "False")
                }
                setPadding(0, 16.dpToPx(), 0, 16.dpToPx())
                textSize = 16f  // Reduced for better readability
                layout.addView(this)
            }
        }
    }

    private fun Int.dpToPx(): Int = (this * resources.displayMetrics.density).toInt()
}