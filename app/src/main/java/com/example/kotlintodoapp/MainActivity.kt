package com.example.kotlintodoapp

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.example.kotlintodoapp.DbSetUp.ToDoEntry.Companion.COL_TASK_ITEM
import com.example.kotlintodoapp.DbSetUp.ToDoEntry.Companion.TASK_TABLE
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    // creates list view on initialization
    private lateinit var listView: ListView

    // create database
    private lateinit var dataB: DbHelper

    // creates array list
    private var toDoList = ArrayList<String>()

    // create adapter for list
    private var listAdapter: ArrayAdapter<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //listView attached to xml element list_items by id
        listView = findViewById(R.id.listed_Items)

        //create list on-create
        toDoList = ArrayList()

        //database
        dataB = DbHelper(this)

        //array of object used as data source
        listAdapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, toDoList)
        listView.adapter = listAdapter

        //remove items form list view using lambda
        listView.setOnItemClickListener { _, _, position: Int, _: Long ->
            // lambda function to remove item from list if clicked on
            val db = dataB.writableDatabase
            val task = toDoList[position]

            toDoList.removeAt(position) //removes from list
            listAdapter?.notifyDataSetChanged() //changes in list view
            db.delete(TASK_TABLE, "$COL_TASK_ITEM = ?", arrayOf(task))
            db.close()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        // adds menu bar which will always be present
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }


    fun addNewListItem(@Suppress("UNUSED_PARAMETER") view: View) {

        // button for adding to list
        val editText: EditText = findViewById(R.id.taskText)
        val task = editText.text.toString()
        val db = dataB.writableDatabase

        if (task.isEmpty()) {
            // toast alert if no text present in text field when trying to add task
            Toast.makeText(applicationContext, "Task to enter not present", Toast.LENGTH_SHORT).show()
        } else {
            toDoList.add(task)
            listAdapter?.notifyDataSetChanged() //changes in list view
            val values = ContentValues()
            values.put(COL_TASK_ITEM, task)
            db.insert(TASK_TABLE, null, values)
            db.close()
            editText.setText("")
        }
    }
}



