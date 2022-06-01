package com.example.ageinminutescalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs
import kotlin.time.Duration.Companion.minutes

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btn = findViewById<AppCompatButton>(R.id.datebtn)
        findViewById<TextView>(R.id.tvSelectedDate).text = "Not Selected"
        btn.setOnClickListener {view ->
            clickDatePicker(view)
        }

    }
    fun clickDatePicker(view : View){

        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)


        DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            Toast.makeText(this,"$day/$month/$year selected.",Toast.LENGTH_LONG).show()
            val selectedDate = "$day/${month+1}/$year"
            findViewById<TextView>(R.id.tvSelectedDate).text = selectedDate
            var sdf = SimpleDateFormat("dd/MM/yyyy" , Locale.ENGLISH)

            val date = sdf.parse(selectedDate)


            val currentDate = sdf.format(Date())
            val nowDate = sdf.parse(currentDate)

            println("Time now: ${nowDate.time}")
            println("Time input: ${date.time}")


            val difference: Long = nowDate.time - date.time
            val differenceDates = difference / 60000
            val dayDifference = differenceDates.toString()
            println("Day diff : $dayDifference")
            findViewById<TextView>(R.id.tvResult).text = dayDifference
        },year,month,day).show()
    }
}