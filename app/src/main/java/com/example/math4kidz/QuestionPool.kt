package com.example.math4kidz


import kotlin.collections.ArrayList

object QuestionPool {

    private var thisQuiz : ArrayList<Question>
    private var questionIndex : Int
    private var score : Int

    init  {
        //
        thisQuiz = ArrayList<Question>(12)
        // instantiate full Q pool
        var questionPool : ArrayList<Question> = ArrayList(43)
        // set index and score
        questionIndex = 0
        score = 0

        var default = generateDefaultQuestions()
        //var image = generateImageQuestions()
        var calculator = generateCalculatorQuestions()

        questionPool.addAll(default)
        //questionPool.addAll(image)
        questionPool.addAll(calculator)
        questionPool.shuffle()

        for (i in 0..11)  {
            var item = questionPool.get(i)
            thisQuiz.add(item)
        }

    }

    fun printquiz()  {
        for (b in thisQuiz)  {
            print(b.toString())
        }
    }

    fun addPointToScore()  {
        score += 1
    }

    fun getScore() : Int  {
        return score
    }

    fun resetIndex() : Unit  {
        questionIndex = 0
    }

    fun getQuestionIndex() : Int {
        return questionIndex
    }

    fun peakNextQuestion() : Question  {
        var q = thisQuiz.get(questionIndex) /** MAY NEED TO REMOVE THE + 1 */
        return q
    }

    fun getNextQuestionFromPool(): Question  {
        var q = thisQuiz.get(questionIndex)
        questionIndex += 1
        return q
    }

    private fun generateDefaultQuestions() : ArrayList<Question> {
        var questionPool = ArrayList<Question>()
        //21 questions
        questionPool.add(Question(0, "What number is this?: 102", 1, arrayListOf("One hundred and twelve", "One hundred and two", "One thousand and two", "One hundred and eleven")))
        questionPool.add(Question(1, "What number is this?: 57", 0, arrayListOf("Fifty seven", "Seventy five", "Seventeen", "Sixty seven")))
        questionPool.add(Question(2, "What number is this?: 1011", 3, arrayListOf("Eleven", "One thousand, one hundred and eleven", "One hundred and eleven", "One thousand and eleven")))
        questionPool.add(Question(3, "What what is the fourth number in the 3 times table?", 2, arrayListOf("14", "15", "12", "4")))
        questionPool.add(Question(4, "Whats the missing number? (14, 16, 18, ... , 22)", 3, arrayListOf("19", "21", "16", "20")))
        questionPool.add(Question(5, "What number is this?: 102", 1, arrayListOf("One hundred and twelve", "One hundred and two", "One thousand and two", "One hundred and eleven")))
        questionPool.add(Question(6, "What is the 11th number in the ten times table?", 3, arrayListOf("One hundred", "Eleven", "Eleven hundred", "One hundred and ten")))
        questionPool.add(Question(7, "10 \u00F7 2 equals?", 2, arrayListOf("8", "20", "5", "12")))
        questionPool.add(Question(8, "What's the missing numbers?  (21, 24, ... , ..., 33 ", 3, arrayListOf("27, 33", "25, 27", "22,23", "27, 30")))
        questionPool.add(Question(9, "What number is this?: 102", 1, arrayListOf("One hundred and twelve", "One hundred and two", "One thousand and two", "One hundred and eleven")))
        questionPool.add(Question(10, "What is 3 x 7?", 0, arrayListOf("21", "24", "27", "18")))
        questionPool.add(Question(11, "What is the fourth number of the fourth times table", 1, arrayListOf("Eight", "Sixteen", "Twelve", "15")))
        questionPool.add(Question(12, "Which answer best describes a square?", 2, arrayListOf("A round shape", "A shape with four sides", "A shape with four equal sides", "A three sided shape")))
        questionPool.add(Question(13, "Which answer best describes a cube?", 2, arrayListOf("A 3D rectangle", "A 2D cuboid", "A 3D square", "A shape with 14 edges")))
        questionPool.add(Question(14, "Select the lowest number.", 2, arrayListOf("Sixty seven", "95", "Sixty five", "68")))
        questionPool.add(Question(15, "Select the highest number", 2, arrayListOf("One hundred and six", "One hundred and two", "114", "112")))
        questionPool.add(Question(16, "What is 87 - 11?", 0, arrayListOf("76", "98", "78", "77")))
        questionPool.add(Question(17, "Select the smallest unit of measurement (length)", 2, arrayListOf("Centimetre", "Metre", "Millimetre", "Yard")))
        questionPool.add(Question(18, "Adam has 10 jelly beans in a bag. (4 red, 6 green) Whats the chance of him picking out a green one?", 0, arrayListOf("2/5", "6/10", "10/6", "10/4")))
        questionPool.add(Question(19, "Imagine you're looking at a clock face. The second hand is rotating clockwise. Which direction is moving towards?", 1, arrayListOf("Left", "Right", "Up", "Down")))
        questionPool.add(Question(20, "What is 14 + 14?", 3, arrayListOf("25", "26", "24", "28")))

        return questionPool
    }

    private fun generateImageQuestions() : ArrayList<Question>  {
        var questionPool = ArrayList<Question>()
        questionPool.add(ImageQuestion(21, "What is the time on this clock face?", 2, arrayListOf("12:50", "01:50", "10:03", "03:00"), R.drawable.clock_face0, ""))
        questionPool.add(ImageQuestion(22, "What is the time on this clock face?", 3, arrayListOf("03:13", "02:00", "12:03", "12:15"), R.drawable.clock_face1, ""))
        questionPool.add(ImageQuestion(23, "What is the time on this clock face?", 0, arrayListOf("1:20", "04:01", "01:04", "11:40"), R.drawable.clock_face3, ""))
        questionPool.add(ImageQuestion(24, "What is the time on this clock face?", 1, arrayListOf("07:30", "08:30", "08:06", "05:50"), R.drawable.clock_face2, ""))
        questionPool.add(ImageQuestion(25, "What is the time on this clock face?", 0, arrayListOf("Forty four minutes past 1", "Quarter to one", "44 Minutes past 10", "One forty"), R.drawable.digital_clock_face, "01:44"))
        questionPool.add(ImageQuestion(26, "What is the time on this clock face?", 3, arrayListOf("Quarter past eleven", "16 minutes past one", "11 Minutes past four", "Eleven Sixteen"), R.drawable.digital_clock_face, "11:16"))
        questionPool.add(ImageQuestion(27, "What is the time on this clock face?", 1, arrayListOf("Ten past ten", "Ten twenty two", "Twenty two past two", "Ten to ten"), R.drawable.digital_clock_face, "10:22"))
        questionPool.add(ImageQuestion(28, "What is the time on this clock face?", 3, arrayListOf("Ten to one", "Two fifty seven", "Midnight", "Twelve fifty seven"), R.drawable.digital_clock_face, "12:57"))
        questionPool.add(ImageQuestion(29, "What is the time on this clock face?", 3, arrayListOf("Six minutes to eight", "Half past six", "Twenty eight minutes to 6", "28 minutes past six"), R.drawable.digital_clock_face, "06:28"))
        questionPool.add(ImageQuestion(30, "What is the time on this clock face?", 0, arrayListOf("Four past Four ", "Four forty", "40 past four", "Four forty"), R.drawable.digital_clock_face, "04:04"))
        questionPool.add(ImageQuestion(31, "What is the time on this clock face?", 2, arrayListOf("5 to 6", "Quarter to 6:00", "Fifty five minutes past four", "Fifty five minutes to four"), R.drawable.digital_clock_face, "16:55"))


        return questionPool
    }

    private fun generateCalculatorQuestions() : ArrayList<Question>  {
        // 11 questions
        var questionPool = ArrayList<Question>()
        val questionString: String = "Please fill in the blanks."
        questionPool.add(CalculatorQuestion(32, questionString, 7, arrayListOf("2", "7", "14", "8", "3", "2", "49"), Operation.MULTIPLY))
        questionPool.add(CalculatorQuestion(33, questionString, 2, arrayListOf("4", "7", "10", "8", "19", "21", "13"), Operation.MULTIPLY))
        questionPool.add(CalculatorQuestion(34, questionString, 6, arrayListOf("2", "7", "3", "8", "4", "21", "12"), Operation.MULTIPLY))
        questionPool.add(CalculatorQuestion(35, questionString, 10, arrayListOf("4", "3", "22", "18", "11", "9", "40"), Operation.MULTIPLY))
        questionPool.add(CalculatorQuestion(36, questionString, 43, arrayListOf("4", "7", "10", "5", "19", "38", "50"), Operation.ADD))
        questionPool.add(CalculatorQuestion(37, questionString, 19, arrayListOf("27", "7", "10", "8", "11", "21", "13"), Operation.ADD))
        questionPool.add(CalculatorQuestion(38, questionString, 3, arrayListOf("4", "2", "6", "3", "1", "20", "-1"), Operation.SUBTRACT))
        questionPool.add(CalculatorQuestion(39, questionString, 23, arrayListOf("30", "27", "4", "8", "15", "7", "13"), Operation.SUBTRACT))
        questionPool.add(CalculatorQuestion(40, questionString, 18, arrayListOf("40", "", "10", "8", "10", "22", "13"), Operation.SUBTRACT))
        questionPool.add(CalculatorQuestion(41, questionString, 10, arrayListOf("4", "7", "100", "5", "10", "21", "2"), Operation.DIVIDE))
        questionPool.add(CalculatorQuestion(42, questionString, 8, arrayListOf("4", "24", "3", "8", "19", "2", "5"), Operation.DIVIDE))

        return questionPool
    }



}