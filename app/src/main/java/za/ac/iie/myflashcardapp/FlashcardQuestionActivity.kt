package za.ac.iie.myflashcardapp

import android.content.Intent
import android.os.Bundle
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

        // Restore state
        if (savedInstanceState != null) {
            currentQuestionIndex = savedInstanceState.getInt("CURRENT_INDEX", 0)
            score = savedInstanceState.getInt("SCORE", 0)
            findViewById<Button>(R.id.button2).isEnabled = savedInstanceState.getBoolean("TRUE_BTN_STATE", true)
            findViewById<Button>(R.id.button3).isEnabled = savedInstanceState.getBoolean("FALSE_BTN_STATE", true)
            findViewById<Button>(R.id.button4).isEnabled = savedInstanceState.getBoolean("NEXT_BTN_STATE", false)
        }

        questions = arrayOf(
            "Nelson Mandela was the president in 1994?",
            "World War I ended in 1918.",
            "The Berlin Wall fell in 1989.",
            "The Titanic sank in 1912.",
            "The Declaration of Independence was signed in 1776."
        )
        answers = booleanArrayOf(true, true, true, true, true)
        userAnswers = BooleanArray(questions.size) { false }

        setupUI()
    }

    private fun setupUI() {
        displayQuestion()

        val trueButton = findViewById<Button>(R.id.button2)
        val falseButton = findViewById<Button>(R.id.button3)
        val nextButton = findViewById<Button>(R.id.button4)

        trueButton.setOnClickListener {
            checkAnswer(true)
            nextButton.isEnabled = true
            trueButton.isEnabled = false
            falseButton.isEnabled = false
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
            nextButton.isEnabled = true
            trueButton.isEnabled = false
            falseButton.isEnabled = false
        }

        nextButton.setOnClickListener {
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
        outState.putInt("CURRENT_INDEX", currentQuestionIndex)
        outState.putInt("SCORE", score)
    }

    private fun displayQuestion() {
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
        findViewById<TextView>(R.id.textView4).text = feedback
    }

    private fun resetButtons() {
        findViewById<Button>(R.id.button2).isEnabled = true
        findViewById<Button>(R.id.button3).isEnabled = true
        findViewById<Button>(R.id.button4).isEnabled = false
        findViewById<TextView>(R.id.textView4).text = ""
    }

    private fun navigateToScoreScreen() {
        Intent(this, ScoreActivity::class.java).apply {
            putExtra("SCORE", score)
            putStringArrayListExtra("QUESTIONS", ArrayList(questions.toList()))
            putExtra("USER_ANSWERS", userAnswers)
            startActivity(this)
        }
    }
}











