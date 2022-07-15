package com.example.math4kidz

import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


/* Database Config*/
private val DataBaseName = "Math4Kidz.db"
private val ver : Int = 1



class DatabaseHelper(context: Context) : SQLiteOpenHelper(context,DataBaseName,null ,ver) {

    /* User Table */
    private val usersTableName = "User"
    private val userId = "UserID"
    private val firstName = "FirstName"
    private val lastName = "LastName"
    private val latestGrade = "-"
    private val quizTableName = "Quiz"
    private val quizId = "QuizID"
    private val grade = "GradeAchieved"

    // This is called the first time a database is accessed
    // Create a new database
    override fun onCreate(db: SQLiteDatabase?) {

        try {
            var sqlCreateUser : String = "CREATE TABLE \"$usersTableName\" ( " +
                    "\"$userId\" INTEGER, \"$firstName\" TEXT NOT NULL" +
                    "\"$lastName\" TEXT NOT NULL, $latestGrade TEXT," +
                    "PRIMARY KEY (\"$userId\" AUTOINCREMENT) );"
            db?.execSQL(sqlCreateUser)

            var sqlCreateQuiz : String = "CREATE TABLE \"$quizId\" (" +
                    "\"$quizId\" INTEGER," +
                    "\"$userId\" INTEGER NOT NULL, " +
                    "\"$grade\" TEXT NOT NULL, " +
                    "PRIMARY KEY(\"$quizId\" AUTOINCREMENT) );"
            db?.execSQL(sqlCreateQuiz)
        }
        catch(e : SQLException) {  }

    }

    // This is called if the database ver. is changed
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun getAllUsers(): ArrayList<User> {
        val userList = ArrayList<User>()
        val db: SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT * FROM $usersTableName"

        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val firstName: String = cursor.getString(1)
                val lastName: String = cursor.getString(2)

                val u = User(id, firstName, lastName, false)
                userList.add(u)
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return userList
    }

    fun getUser(id : Int) : User {
        val db : SQLiteDatabase = this.readableDatabase
        val sqlStatement = "SELECT $firstName, $lastName, $latestGrade FROM $usersTableName WHERE \"$userId\" = $id ;"
        val cursor: Cursor = db.rawQuery(sqlStatement, null)

        var user = User()
        if (cursor.moveToFirst())
            do {
                val id: Int = cursor.getInt(0)
                val firstName: String = cursor.getString(1)
                val lastName: String = cursor.getString(2)
                val grade = cursor.getString(3)
                var lg : Char = grade[0]
                user = User(id, firstName, lastName, false)
                user.latestGrade = lg
            } while (cursor.moveToNext())

        cursor.close()
        db.close()

        return user
    }

    


}