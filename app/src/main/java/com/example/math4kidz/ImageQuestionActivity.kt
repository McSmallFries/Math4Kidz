package com.example.math4kidz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ImageQuestionActivity : AppCompatActivity(), View.OnClickListener {

    val thisQuiz = QuestionPool
    private val thisQuestion = thisQuiz.getNextQuestionFromPool()
            as ImageQuestion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_question)

        // check if quiz finished
        val isFinalQuestion = thisQuiz.getQuestionIndex() == 12
        if (isFinalQuestion)  {
            endQuiz()
        }

        println("Creating image question")

        // get UI
        val questionText = findViewById<TextView>(R.id.tvQuestionText)
        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)

        val buttons = listOf(btn1, btn2, btn3, btn4)

        // set button properties
        var qNum = thisQuiz.getQuestionIndex().toString()
        var que = thisQuestion.question
        questionText.text = "Q$qNum: $que"

        // process the buttons
        for ((i, btn) in buttons.withIndex())  {
            btn.text = thisQuestion.userAnswers.get(i)
            btn.setOnClickListener(this)
        }

        if (thisQuestion.getClockface().equals(""))  {
            val imageView = findViewById<ImageView>(R.id.ivImageQuestion)
            val image = thisQuestion.getImage()
            imageView.setBackgroundResource(image)
            imageView.maxWidth = 100
            imageView.maxHeight = 100
            println("Creating image question analogue clock....img: $image")
        }
        else {
            val imageView = findViewById<ImageView>(R.id.ivImageQuestion)
            val tvTime = findViewById<TextView>(R.id.tvTime)
            val image = thisQuestion.getImage()
            val time = thisQuestion.getClockface()
            imageView.setImageResource(image)
            tvTime.text = time
            println("Creating image question digital clock....img: $image")
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

        val intent = Intent(this, location)
        startActivity(intent)
    }


    fun endQuiz() : Unit  {
        val grade = markQuiz()
        val intent = Intent(this, ProgressActivity::class.java)
        var message: String
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

    override fun onClick(v: View?) {
        // if (v != null)
        if (v is Button)  {
            // get correct ans with index
            val correctAns = thisQuestion.userAnswers.get(thisQuestion.answer)
            // get button.text
            val txt = v.text

            //check if correct
            if (txt.equals(correctAns))  {
                // correct answer, score a point -> move to next activity
                thisQuiz.addPointToScore()
                nextQuestion()
            }
            else {
                // move to next activity
                // no point
                nextQuestion()
            }
        }
    }


}