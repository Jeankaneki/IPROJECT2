package za.ac.iie.myflashcardapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class FlashcardQuestionActivity : AppCompatActivity() {

    private lateinit var questions: Array<String>
    private lateinit var answers: BooleanArray
    private lateinit var userAnswers: BooleanArray
    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_flashcard_question)

        // Initialize questions and answers
        questions = arrayOf(
            "Nelson Mandela was the president in 1994?",
            "World War I ended in 1918.",
            "The Berlin Wall fell in 1989.",
            "The Titanic sank in 1912.",
            "The Declaration of Independence was signed in 1776."
        )
        answers = booleanArrayOf(true, true, true, true, true)

        // Restore state if available
        if (savedInstanceState != null) {
            currentQuestionIndex = savedInstanceState.getInt("CURRENT_INDEX", 0)
            score = savedInstanceState.getInt("SCORE", 0)
            userAnswers = savedInstanceState.getBooleanArray("USER_ANSWERS") ?: BooleanArray(questions.size)
            // Restore button states
            findViewById<Button>(R.id.button2).isEnabled = savedInstanceState.getBoolean("TRUE_BTN_STATE", true)
            findViewById<Button>(R.id.button3).isEnabled = savedInstanceState.getBoolean("FALSE_BTN_STATE", true)
            findViewById<Button>(R.id.button4).isEnabled = savedInstanceState.getBoolean("NEXT_BTN_STATE", false)
            Log.d("Flashcard", "State restored - Index: $currentQuestionIndex, Score: $score")
        } else {
            userAnswers = BooleanArray(questions.size) { false }
            Log.d("Flashcard", "New session started")
        }

        setupUI()
    }

    private fun setupUI() {
        displayQuestion()

        val trueButton = findViewById<Button>(R.id.button2)
        val falseButton = findViewById<Button>(R.id.button3)
        val nextButton = findViewById<Button>(R.id.button4)

        trueButton.setOnClickListener {
            Log.d("Flashcard", "True button clicked for question ${currentQuestionIndex + 1}")
            checkAnswer(true)
            nextButton.isEnabled = true
            trueButton.isEnabled = false
            falseButton.isEnabled = false
        }

        falseButton.setOnClickListener {
            Log.d("Flashcard", "False button clicked for question ${currentQuestionIndex + 1}")
            checkAnswer(false)
            nextButton.isEnabled = true
            trueButton.isEnabled = false
            falseButton.isEnabled = false
        }

        nextButton.setOnClickListener {
            Log.d("Flashcard", "Next button clicked")
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                displayQuestion()
                resetButtons()
            } else {
                navigateToScoreScreen()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Save current state
        outState.putInt("CURRENT_INDEX", currentQuestionIndex)
        outState.putInt("SCORE", score)
        outState.putBooleanArray("USER_ANSWERS", userAnswers)
        // Save button states
        outState.putBoolean("TRUE_BTN_STATE", findViewById<Button>(R.id.button2).isEnabled)
        outState.putBoolean("FALSE_BTN_STATE", findViewById<Button>(R.id.button3).isEnabled)
        outState.putBoolean("NEXT_BTN_STATE", findViewById<Button>(R.id.button4).isEnabled)
        Log.d("Flashcard", "State saved - Index: $currentQuestionIndex, Score: $score")
    }

    private fun displayQuestion() {
        Log.d("Flashcard", "Displaying question ${currentQuestionIndex + 1}")
        findViewById<TextView>(R.id.textView3).text = questions[currentQuestionIndex]
    }

    private fun checkAnswer(userAnswer: Boolean) {
        userAnswers[currentQuestionIndex] = userAnswer
        val correctAnswer = answers[currentQuestionIndex]
        val feedback = if (userAnswer == correctAnswer) {
            score++
            "Correct!"
        } else {
            "Incorrect."
        }
        Log.d("Flashcard", "Feedback: $feedback for question ${currentQuestionIndex + 1}")
        findViewById<TextView>(R.id.textView4).text = feedback
    }

    private fun resetButtons() {
        findViewById<Button>(R.id.button2).isEnabled = true
        findViewById<Button>(R.id.button3).isEnabled = true
        findViewById<Button>(R.id.button4).isEnabled = false
        findViewById<TextView>(R.id.textView4).text = ""
        Log.d("Flashcard", "Buttons reset for next question")
    }
// In FlashcardQuestionActivity's navigateToScoreScreen()

    private fun navigateToScoreScreen() {
        Log.d("Flashcard", "Navigating to ScoreActivity. Final score: $score/${questions.size}")
        Intent(this, ScoreActivity::class.java).apply {
            putExtra("SCORE", score)
            putStringArrayListExtra("QUESTIONS", ArrayList(questions.toList()))
            putExtra("USER_ANSWERS", userAnswers)
            putExtra("CORRECT_ANSWERS", answers)  // Add this line
            startActivity(this)
        }
    }
}