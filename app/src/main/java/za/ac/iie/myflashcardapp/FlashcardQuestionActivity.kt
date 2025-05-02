package za.ac.iie.myflashcardapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class FlashcardQuestionActivity : AppCompatActivity() {

    // Logging tag
    private val TAG = "FlashcardActivity"

    // Quiz data arrays
    private lateinit var questions: Array<String>
    private lateinit var correctAnswers: BooleanArray
    private lateinit var userAnswers: BooleanArray

    // Game state
    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashcard_question)
        Log.d(TAG, "Activity created")

        // Initialize quiz data
        questions = arrayOf(
            "Nelson Mandela was the president in 1994?",
            "World War I ended in 1918.",
            "The Declaration of Independence of the USA was signed in 1776.",
            "The Titanic sank in 1912.",
            "The Great Wall is visible from space?"
        )

        correctAnswers = booleanArrayOf(true, true, true, true, false)
        userAnswers = BooleanArray(questions.size)

        // Restore state if available
        if (savedInstanceState != null) {
            currentQuestionIndex = savedInstanceState.getInt("CURRENT_INDEX")
            score = savedInstanceState.getInt("SCORE")
            userAnswers = savedInstanceState.getBooleanArray("USER_ANSWERS") ?: BooleanArray(questions.size)
            Log.d(TAG, "State restored - Question: ${currentQuestionIndex + 1}, Score: $score")
        }

        setupUI()
    }

    private fun setupUI() {
        // Initialize UI components
        val trueButton: Button = findViewById(R.id.button2)
        val falseButton: Button = findViewById(R.id.button3)
        val nextButton: Button = findViewById(R.id.button4)
        displayQuestion()

        // Set up button listeners
        trueButton.setOnClickListener {
            Log.d(TAG, "True selected for question ${currentQuestionIndex + 1}")
            handleAnswer(true)
        }

        falseButton.setOnClickListener {
            Log.d(TAG, "False selected for question ${currentQuestionIndex + 1}")
            handleAnswer(false)
        }

        nextButton.setOnClickListener {
            Log.d(TAG, "Moving to next question")
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                displayQuestion()
                resetButtons()
            } else {
                endQuiz()
            }
        }
    }

    private fun displayQuestion() {
        Log.d(TAG, "Displaying question ${currentQuestionIndex + 1}")
        findViewById<TextView>(R.id.textView3).text = questions[currentQuestionIndex]
    }

    private fun handleAnswer(userAnswer: Boolean) {
        // Record answer and update UI
        userAnswers[currentQuestionIndex] = userAnswer
        val isCorrect = (userAnswer == correctAnswers[currentQuestionIndex])

        findViewById<TextView>(R.id.textView4).text = if (isCorrect) {
            score++
            "Correct!"
        } else {
            "Incorrect!"
        }

        // Disable answer buttons and enable next
        listOf(R.id.button2, R.id.button3).forEach {
            findViewById<Button>(it).isEnabled = false
        }
        findViewById<Button>(R.id.button4).isEnabled = true
    }

    private fun resetButtons() {
        // Reset UI for next question
        listOf(R.id.button2, R.id.button3).forEach {
            findViewById<Button>(it).isEnabled = true
        }
        findViewById<Button>(R.id.button4).isEnabled = false
        findViewById<TextView>(R.id.textView4).text = ""
    }

    private fun endQuiz() {
        Log.d(TAG, "Quiz ended - Final score: $score/${questions.size}")
        Intent(this, ScoreActivity::class.java).apply {
            putExtra("SCORE", score)
            putExtra("TOTAL_QUESTIONS", questions.size)
            putExtra("USER_ANSWERS", userAnswers)
            putExtra("CORRECT_ANSWERS", correctAnswers)
            putStringArrayListExtra("QUESTIONS", ArrayList(questions.toList()))
            startActivity(this)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save game state
        outState.putInt("CURRENT_INDEX", currentQuestionIndex)
        outState.putInt("SCORE", score)
        outState.putBooleanArray("USER_ANSWERS", userAnswers)
        Log.d(TAG, "Saving state - Question: ${currentQuestionIndex + 1}, Score: $score")
    }
}
