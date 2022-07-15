package com.example.math4kidz

class ImageQuestion(
    id: Int, // id
    question: String,
    answer: Int,
    userAnswers: ArrayList<String>,
    private val image: Int,
    private val clockface : String) : Question (id, question, answer, userAnswers) {

        constructor() : this(0, "", 1, ArrayList<String>(),
            R.drawable.clock_face, "")

        fun getImage() : Int {
            return image
        }

        fun getClockface() : String  {
            return clockface
        }

        override fun toString() : String  {
            return "$question, cl: $clockface, img: $image"
        }
    }