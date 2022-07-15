package com.example.math4kidz

open class Question (id: Int, question: String, answer : Int, userAnswers : ArrayList<String>)  {

    /** The id of the question allowing each object of this class to be identified */
    val id : Int  = id

    /** The question string to be asked */
    val question = question

    /** The index of the correct answer within the userAnswers list */
    val answer = answer

    /** An array of answers to be displayed to the user in no particular order */
    val userAnswers = userAnswers


    /** default constructor for when the object needs constructing before setting vals */
    constructor() : this (0, "", 1, ArrayList<String>())  {}

    override fun toString(): String {
        return "$question\n"
    }


}