package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var firstNum: Double = 0.0
        var secondNum: Double = 0.0
        var operationID: Int = 0
        var allNumbersIn = false

        val valueInput = findViewById<EditText>(R.id.input_field)
        val plusBtn = findViewById<Button>(R.id.plus_btn)
        val resultBtn = findViewById<Button>(R.id.result_btn)

        plusBtn.setOnClickListener {

                operationID = 1
                firstNum = valueInput.text.toString().toDouble()
                valueInput.text.clear()
                allNumbersIn = true

        }

        resultBtn.setOnClickListener {

            if (allNumbersIn){

                secondNum = valueInput.text.toString().toDouble()
                valueInput.text.clear()
                var obj = sendToCalc(firstNum, secondNum, operationID)
                Toast.makeText(this@MainActivity, obj.result.toString(), Toast.LENGTH_LONG).show()
                allNumbersIn = false

            }
        }
    }

    fun sendToCalc(firstNum: Double, secondNum: Double, operationID: Int): Calculator{

        var operation = Calculator(firstNum, secondNum, operationID)
        return  operation
    }
}