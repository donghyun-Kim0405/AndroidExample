package com.example.spinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.get

class MainActivity : AppCompatActivity() {

    lateinit var textView : TextView
    lateinit var spinner : Spinner
    lateinit var items : ArrayList<String>
    lateinit var adapter : ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        spinner = findViewById(R.id.spinner)

        items = ArrayList<String>()
        for(i in 0..10){
            items.add(i.toString())
        }

        adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener=object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                textView.text = items[p2].toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(baseContext, "nothing selected!", Toast.LENGTH_SHORT).show()
            }
        }

    }

}