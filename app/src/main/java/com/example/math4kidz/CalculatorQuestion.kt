package com.example.math4kidz


class CalculatorQuestion(
    id: Int, // id
    question: String, // "complete the equation below using the numbers" 1 string that will be passed to all objects of this class
    answer: Int, // store the provided field here
    userAnswers: ArrayList<String>, // store numbers to choose from here
    private val operation: Operation
    ) : Question(id, question, answer, userAnswers)  {

    constructor() : this(0, "", 1, ArrayList<String>(), Operation.MULTIPLY) {}

    /**
       Pass the user's entry (with the provided field) to this function to determine
       if this Question object is correct or incorrect
     */
    fun calculateResult(num1 : Int, num2 : Int, answer: Int) : Boolean  {
        when (operation)  {
            Operation.MULTIPLY -> return num1 * num2 == answer
            Operation.DIVIDE -> return num1 / num2 == answer
            Operation.ADD -> return num1 + num2 == answer
            Operation.SUBTRACT -> return num1 - num2 == answer
        }
    }

    fun fetchOperation() : Operation  {
        return operation
    }


}