package com.example.math4kidz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import org.w3c.dom.Text
import kotlin.properties.Delegates

class CalculatorQuestionActivity : AppCompatActivity(), View.OnClickListener {

    private val thisQuiz = QuestionPool
    private val thisQuestion = thisQuiz.getNextQuestionFromPool()
            as CalculatorQuestion
    private var givenAnswer = thisQuestion.answer
    private val givenAnswerIndex = (0..2).random()

    // give ui class level scope for when
    lateinit var tvNum1 : TextView
    lateinit var tvNum2 : TextView
    lateinit var tvResult : TextView
    lateinit var buttons : List<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator_question)

        // check to see if quiz is finished
        val isFinalQuestion = thisQuiz.getQuestionIndex() == 12
        if (isFinalQuestion)  {
            endQuiz()
        }

        // get ui
        tvNum1 = findViewById(R.id.tvNum1)
        tvNum2 = findViewById(R.id.tvNum2)
        tvResult = findViewById(R.id.tvResult)
        val tvOperation = findViewById<TextView>(R.id.tvOperation)
        tvOperation.text = mapOperationToCharacter(thisQuestion.fetchOperation())

        // question text
        val questionText = findViewById<TextView>(R.id.tvCalcQT)
        // display question data on screen
        var qNum = thisQuiz.getQuestionIndex().toString()
        var que = thisQuestion.question
        questionText.text =  "Q$qNum: $que"

        // put textview in list so we can assign the pre-filled answer randomly
        val tvList = listOf(tvNum1, tvNum2, tvResult)
        tvList.get(givenAnswerIndex).text = givenAnswer.toString()


        // store buttons in list so they can be processed
        val btn1 = findViewById<Button>(R.id.btn_0_0)
        val btn2 = findViewById<Button>(R.id.btn_1_0)
        val btn3 = findViewById<Button>(R.id.btn_2_0)
        val btn4 = findViewById<Button>(R.id.btn_0_1)
        val btn5 = findViewById<Button>(R.id.btn_1_1)
        val btn6 = findViewById<Button>(R.id.btn_2_1)
        val btn7 = findViewById<Button>(R.id.btn_1_2)

        // store non numeric buttons
        val btnReset = findViewById<Button>(R.id.btnReset)
        val btnNext = findViewById<Button>(R.id.btnNext)

        buttons = listOf(btn1, btn2, btn3, btn4, btn5, btn6, btn7)

        // process numeric buttons
        for ((i, btn) in buttons.withIndex())  {
            btn.text = thisQuestion.userAnswers.get(i)
            btn.setOnClickListener(this)
        }

        // reset button listener
        btnReset.setOnClickListener {
            resetButtons()
            resetQuestionTVs()
        }

        btnNext.setOnClickListener {

            if (!tvNum1.text.equals("") && !tvNum2.text.equals("")
                && !tvResult.text.equals(""))  {

                // increment score if ans is correct
                if (isAnswerCorrect())
                    thisQuiz.addPointToScore()


                // get next question from quiz and take user
                // to corresponding activity
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
        }
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

    fun mapOperationToCharacter(op : Operation) : String {
        when (op)  {
            Operation.ADD -> return "+"
            Operation.SUBTRACT -> return "-"
            Operation.MULTIPLY -> return "\u00D7"
            Operation.DIVIDE -> return "\u00F7"
        }
    }

    fun isAnswerCorrect() : Boolean  {
        // store the info needed to make a sum
        // parse int text value
        val num1 : Int = Integer.parseInt(tvNum1.text as String)
        val num2 : Int = Integer.parseInt(tvNum2.text as String)
        val result : Int = Integer.parseInt(tvResult.text as String)
        val operation = thisQuestion.fetchOperation()

        // evaluate the sum
        when (operation)  {
            Operation.MULTIPLY -> return num1 * num2 == result
            Operation.DIVIDE -> return num1 / num2 == result
            Operation.ADD -> return num1 + num2 == result
            Operation.SUBTRACT -> return num1 - num2 == result
        }

    }

    fun resetButtons()   {
        // store drawables in list
        val colors = listOf(R.drawable.btn_bg_blue, R.drawable.btn_bg_green,
            R.drawable.btn_bg_teal, R.drawable.btn_bg_orange)
        // store index of what the colors were to be used to get them back
        val indexes = listOf (3, 0, 1, 3, 2, 3, 3)

        // cycle thru buttons and restore them
        for ((i, btn) in buttons.withIndex())  {
            btn.text = thisQuestion.userAnswers.get(i)
            //  set the background back to what they were
            btn.setBackgroundResource(colors.get(indexes.get(i)))
        }
    }

    fun resetQuestionTVs()  {
        when (givenAnswerIndex)  {
            0 -> {
                tvNum1.text = givenAnswer.toString() // change others back to empty
                tvNum2.text = ""
                tvResult.text = ""
            }
            1 -> {
                tvNum2.text = givenAnswer.toString()
                tvNum1.text = ""
                tvResult.text = ""
            }
            2 ->  {
                tvResult.text = givenAnswer.toString()
                tvNum1.text = ""
                tvNum2.text = ""
            }
        }
    }

    override fun onClick(v: View?) {
        if (v is Button)  {
            val correctAns = thisQuestion.answer
            val btnText = v.text

            if (tvNum1.text.equals(""))  {
                // assign the text of button to this tv
                tvNum1.text = btnText
                v.setBackgroundResource(R.drawable.border)
            }
            else if (tvNum2.text.equals(""))  {
                tvNum2.text = btnText
                v.setBackgroundResource(R.drawable.border)
            }
            else if (tvResult.text.equals(""))  {
                tvResult.text = btnText
                v.setBackgroundResource(R.drawable.border)
            }
        }
    }
}