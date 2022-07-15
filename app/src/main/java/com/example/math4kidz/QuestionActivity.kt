package com.example.math4kidz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Button
import android.widget.TextView

class QuestionActivity : AppCompatActivity(), View.OnClickListener {

    val thisQuiz = QuestionPool;
    private val thisQuestion = thisQuiz.getNextQuestionFromPool()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        // check to see if quiz is finished
        val isFinalQuestion = thisQuiz.getQuestionIndex() == 12
        if (isFinalQuestion)  {
            endQuiz()
        }

        // get UI
        val questionText = findViewById<TextView>(R.id.tvQuestionText)

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)

        val buttons = listOf(btn1, btn2, btn3, btn4)

        // display question data on screen
        var qNum = thisQuiz.getQuestionIndex().toString()
        var que = thisQuestion.question
        questionText.text =  "Q$qNum: $que"

        // process the buttons
        for ((i, btn) in buttons.withIndex())  {
            btn.text = thisQuestion.userAnswers.get(i)
            btn.setOnClickListener(this)
        }
    }

    fun endQuiz() : Unit  {
        val grade = markQuiz()
        val intent = Intent(this, ProgressActivity::class.java)
        var message = intent.getStringExtra(AlarmClock.EXTRA_MESSAGE)
        when (grade)  {
            'U', 'E', 'D' -> message = "Better luck next time!"
            else -> message = "Congratulations!"
        }
        message += "Your final grade for this test was: $grade"
        intent.putExtra(AlarmClock.EXTRA_MESSAGE, "Congratulations")
        startActivity(intent)
    }

    fun markQuiz() : Char  {
        val score = thisQuiz.getScore()
        when (score)  {
            0, 1 -> return 'U'
            2, 3 -> return 'E'
            4, 5 -> return 'D'
            6, 7 -> return 'C'
            8, 9 -> return 'B'
            else -> return 'A'
        }
    }

    fun nextQuestion()  {
        val nextQuestion = thisQuiz.peakNextQuestion()
        var location : Class<*> = Question::class.java

        when (nextQuestion)  {
            is CalculatorQuestion -> location = CalculatorQuestionActivity::class.java
            is Question -> location = QuestionActivity::class.java
            is ImageQuestion -> location = ImageQuestionActivity::class.java
        }

        println("location: $location")

        val intent = Intent(this, location)
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        // if (v != null)
        if (v is Button)  { // maybe unecessary too
            val correctAns = thisQuestion.userAnswers.get(thisQuestion.answer)
            val txt = v.text
            if (txt.equals(correctAns))  {
                // correct answer, score a point -> move to next activity
                thisQuiz.addPointToScore()
                nextQuestion()
            }
            else {
                // move to next activity
                nextQuestion()
            }
        }
    }


}