package com.example.kotlintodoapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context) : SQLiteOpenHelper(context, DbSetUp.DB_NAME, null, DbSetUp.DB_VERSION) {

    // create table
    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE " + DbSetUp.ToDoEntry.TASK_TABLE + " ( " +
                DbSetUp.ToDoEntry.TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DbSetUp.ToDoEntry.COL_TASK_ITEM + " TEXT NOT NULL);"

        db.execSQL(createTable)
    }

    // remove table if already exist
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + DbSetUp.ToDoEntry.TASK_TABLE)
        onCreate(db)
    }
}