package com.sahilbehl4.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvDateInMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.buttonDatePicker)
        tvSelectedDate = findViewById(R.id.selectedDate)
        tvDateInMinutes = findViewById(R.id.dateInMinutes)

        button.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dpdp = DatePickerDialog(this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->

                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate?.text = selectedDate
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val date = sdf.parse(selectedDate)

                date?.let {
                    val selectedDateInMinutes = date.time / 60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    val currentDateInMinutes = currentDate.time / 60000

                    val difference = currentDateInMinutes - selectedDateInMinutes

                    tvDateInMinutes?.text = "$difference"
                }
            },
            year,
            month,
            day)

        dpdp.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpdp.show()
    }
}