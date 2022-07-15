package com.example.math4kidz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.Button


class MainActivity : AppCompatActivity() {

    /**
     * The quiz object is initialised when application is loaded.
     */
    val thisQuiz = QuestionPool

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // val btnNext = findViewById<Button>(R.id.btnNext)
        val btnStart = findViewById<Button>(R.id.btnStart)

        thisQuiz.printquiz()

        btnStart.setOnClickListener {
            val question = thisQuiz.peakNextQuestion()
            thisQuiz.resetIndex()
            var location : Class<*> = Question::class.java


            when(question) {
                is CalculatorQuestion -> location = CalculatorQuestionActivity::class.java
                is Question -> location = QuestionActivity::class.java
                is ImageQuestion -> location = ImageQuestionActivity::class.java
            }
            println("location: $location")
            // initialise the value for the quiz question number
            val qNumber = thisQuiz.getQuestionIndex() + 1
            val intent = Intent(this, location)
            val message = intent.getStringExtra(EXTRA_MESSAGE)
            intent.putExtra(EXTRA_MESSAGE, qNumber.toString())
            startActivity(intent)
        }
    }
}