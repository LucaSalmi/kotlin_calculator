package com.example.myapplication

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {


    var resultString = ""
    var operationString = ""
    var buffer = ""
    val operationArray = mutableListOf<String>()

    var isSymbol = false
    var erase = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFieldsAndListeners()

    }

    private fun setFieldsAndListeners() {

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

            operationArray.add(buffer)
            buffer = ""
            var obj = sendToCalc()
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            resultString = df.format(obj.result)
            updateFields(opViewer)

        }
    }

    private fun buttonAction(symbId: Int, view: TextView) {

        when (symbId) {

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
            10 -> {
                changeBool(0)
                stringMaker("+", view)
            }
            11 -> {
                changeBool(0)
                stringMaker("-", view)
            }
            12 -> {
                changeBool(0)
                stringMaker ("*", view)
            }
            13 -> {
                changeBool(0)
                stringMaker("/", view)
            }
            14 -> {
                changeBool(0)
                stringMaker(resultString, view)
            }
            15 -> {
                changeBool(1)
                erase(view)
            }

        }

    }

    private fun sendToCalc(): Calculator {

        return Calculator(operationArray)
    }

    private fun changeBool(id: Int) {

        if (id == 0) {
            isSymbol = !isSymbol
        } else erase = !erase
    }

    private fun erase(view: TextView) {

        operationString = operationString.dropLast(1)
        changeBool(1)
        updateFields(view)
    }

    private fun stringMaker(temp: String, view: TextView) {


        operationString = when {

            isSymbol -> {

                changeBool(0)
                operationArray.add(buffer)
                buffer = ""
                operationArray.add(temp)
                "$operationString$temp"

            }
            else -> {
                 buffer = "$buffer$temp"
                "$operationString$temp"
            }
        }

        updateFields(view)
    }

    private fun updateFields(view: TextView) {

        view.text = operationString

    }
}