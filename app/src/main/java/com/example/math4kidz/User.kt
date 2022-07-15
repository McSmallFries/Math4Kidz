package com.example.math4kidz

import android.content.Context

class User {

    //private val context: Context;
    private val id : Int get() { return field }
    private val firstName : String get() { return field }
    private val lastName : String get() { return field }
    private val isAdmin : Boolean get() { return field }
    var latestGrade : Char; get() = field; set(value) { field = value }

    //val dbHelper : DatabaseHelper = DatabaseHelper(context)
    private lateinit var dbHelper : DatabaseHelper

    constructor()  {
        //context = context
        id = 0
        firstName = ""
        lastName = ""
        isAdmin = false
        latestGrade = '-'
    }

    constructor(id : Int, fn : String, ln: String, admin: Boolean)  {

        // needs to be retrieved from db
        this.id = id
        firstName = fn
        lastName = ln
        isAdmin = admin
        latestGrade = '-'
    }

    fun setdbHelper(context : Context)  {
        dbHelper = DatabaseHelper(context)
    }


}
