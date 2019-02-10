package com.example.kotlintodoapp

import android.provider.BaseColumns

class DbSetUp {

    // table info
    companion object {
        val DB_NAME = "com.example.kotlintodoapp.dbase"
        val DB_VERSION = 3
    }

    class ToDoEntry : BaseColumns {

        // table set up
        companion object {
            val TASK_TABLE = "tasks"
            val COL_TASK_ITEM = "task"
            val TASK_ID = BaseColumns._ID
        }
    }

}
