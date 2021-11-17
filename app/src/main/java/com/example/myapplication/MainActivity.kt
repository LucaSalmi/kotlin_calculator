package com.example.myapplication

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {


    var resultString = ""
    var operationString = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFieldsAndListeners()

    }

    fun setFieldsAndListeners(){

        val opViewer = findViewById<TextView>(R.id.operation_view)

        val plusBtn = findViewById<Button>(R.id.plus_btn)
        val minusBtn = findViewById<Button>(R.id.minus_btn)
        val multiplyBtn = findViewById<Button>(R.id.multiply_btn)
        val divideBtn = findViewById<Button>(R.id.division_btn)
        val resultBtn = findViewById<Button>(R.id.result_btn)

        val clearBtn = findViewById<Button>(R.id.clear)
        val eraseBtn = findViewById<Button>(R.id.erase)

        val btnOne = findViewById<Button>(R.id.num_1)
        val btnTwo = findViewById<Button>(R.id.num_2)
        val btnThree = findViewById<Button>(R.id.num_3)
        val btnFour = findViewById<Button>(R.id.num_4)
        val btnFive = findViewById<Button>(R.id.num_5)
        val btnSix = findViewById<Button>(R.id.num_6)
        val btnSeven = findViewById<Button>(R.id.num_7)
        val btnEight = findViewById<Button>(R.id.num_8)
        val btnNine = findViewById<Button>(R.id.num_9)
        val btnZero = findViewById<Button>(R.id.num_0)



        btnOne.setOnClickListener {

            buttonAction(1, opViewer)
        }
        btnTwo.setOnClickListener {

            buttonAction(2, opViewer)
        }
        btnThree.setOnClickListener {

            buttonAction(3, opViewer)
        }
        btnFour.setOnClickListener {

            buttonAction(4, opViewer)
        }
        btnFive.setOnClickListener {

            buttonAction(5, opViewer)
        }
        btnSix.setOnClickListener {

            buttonAction(6, opViewer)
        }
        btnSeven.setOnClickListener {

            buttonAction(7, opViewer)
        }
        btnEight.setOnClickListener {

            buttonAction(8, opViewer)
        }
        btnNine.setOnClickListener {

            buttonAction(9, opViewer)
        }
        btnZero.setOnClickListener {

            buttonAction(0, opViewer)
        }


        plusBtn.setOnClickListener {

            buttonAction(10, opViewer)
        }
        minusBtn.setOnClickListener {

            buttonAction(11, opViewer)
        }
        multiplyBtn.setOnClickListener {

            buttonAction(12, opViewer)
        }
        divideBtn.setOnClickListener {

            buttonAction(13, opViewer)
        }

        clearBtn.setOnClickListener {
            operationString = ""
            updateFields(opViewer)
        }

        eraseBtn.setOnClickListener { buttonAction(15, opViewer) }

        resultBtn.setOnClickListener {

            var obj = sendToCalc()
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            resultString = df.format(obj.result)
            updateFields(opViewer)

        }
    }

    fun buttonAction(symbId: Int, view: TextView){

        when(symbId){

            0 -> stringMaker("0", view)
            1 -> stringMaker("1", view)
            2 -> stringMaker("2", view)
            3 -> stringMaker("3", view)
            4 -> stringMaker("4", view)
            5 -> stringMaker("5", view)
            6 -> stringMaker("6", view)
            7 -> stringMaker("7", view)
            8 -> stringMaker("8", view)
            9 -> stringMaker("9", view)
            10 -> stringMaker("+", view)
            11 -> stringMaker("-", view)
            12 -> stringMaker("*", view)
            13 -> stringMaker("/", view)
            14 -> stringMaker(resultString, view)
            15 -> stringMaker("D", view)
        }

    }

    fun sendToCalc(): Calculator {

        return Calculator(operationString)
    }

    fun stringMaker(temp: String, view: TextView) {

        operationString = if (temp == "D"){

            operationString.dropLast(1)

        }else{

            "$operationString$temp"
        }

        Log.d(TAG, "stringMaker: $operationString")
        updateFields(view)
    }

    fun updateFields(view: TextView) {

        view.text = operationString

    }
}