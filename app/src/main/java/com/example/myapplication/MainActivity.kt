package com.example.myapplication

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class MainActivity : AppCompatActivity() {


    var resultString = ""
    var operationString = ""
    var analysisString = ""
    var buffer = ""
    val numbersArray = mutableListOf<Int>()
    val symbolArray = mutableListOf<String>()
    val resultsArray = mutableListOf<String>()

    var isSymbol = false
    var erase = false
    var isResult = false


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

        val opMemory = findViewById<ListView>(R.id.memory_layout)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultsArray)
        opMemory.adapter = adapter


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

            resultBtnAction(opViewer)
            adapter.notifyDataSetChanged()
        }
    }

    private fun resultBtnAction(view: TextView){

        if (operationString.endsWith('+') || operationString.endsWith('-') || operationString.endsWith('*') || operationString.endsWith('/')){

            Toast.makeText(this, "not calculable", Toast.LENGTH_SHORT).show()
            return

        }else "$operationString?"

        unmakeString()

        if (checkForZeroes()){

            Toast.makeText(this, "can't operate with zero", Toast.LENGTH_SHORT).show()
            arrayCleaner()
            return

        }

        if (checkIfEmpty()){

            var obj = sendToCalc()
            resultString = obj.result
            buttonAction(14, view)
            updateFields(view)
            arrayCleaner()

        }else Toast.makeText(this, "not calculable", Toast.LENGTH_SHORT).show()
    }

    private fun checkForZeroes(): Boolean {

        return numbersArray.contains(0)

    }

    private fun checkIfEmpty(): Boolean {

        return numbersArray.size >= 2
    }

    private fun arrayCleaner(){

        numbersArray.clear()
        symbolArray.clear()

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
                changeBool(2)
                stringMaker(resultString, view)
            }
            15 -> {
                changeBool(1)
                erase(view)
            }

        }

    }

    private fun sendToCalc(): Calculator {

        return Calculator(numbersArray, symbolArray)
    }

    private fun changeBool(id: Int) {

        when (id) {
            0 -> isSymbol = !isSymbol
            1 -> erase = !erase
            2 -> isResult = !isResult
        }
    }

    private fun erase(view: TextView) {

        operationString = operationString.dropLast(1)
        changeBool(1)
        updateFields(view)
    }

    private fun addToMemory(temp: String){

        resultsArray.add("$operationString=$temp")


    }

    private fun stringMaker(temp: String, view: TextView) {


        operationString = when {

            isSymbol -> {

                changeBool(0)
                "$operationString$temp"

            }
            isResult -> {

                changeBool(2)
                addToMemory(temp)
                temp

            }else -> {

                "$operationString$temp"
            }
        }

        updateFields(view)
    }

    private fun updateFields(view: TextView) {

        view.text = operationString

    }

    private fun unmakeString(){

        var counter = 0
        analysisString = operationString

        for(c in analysisString){

            when (c){

                '+' -> {
                    symbolArray.add("+")
                }
                '-' -> {
                    symbolArray.add("-")
                }
                '*' -> {
                    symbolArray.add("*")
                }
                '/' -> {
                    symbolArray.add("/")
                }
            }
        }

        analysisString = analysisString.replace('+', '?')
        analysisString = analysisString.replace('-', '?')
        analysisString = analysisString.replace('*', '?')
        analysisString = analysisString.replace('/', '?')
        analysisString = "$analysisString?"

        for(c in analysisString){

            if (c == '?'){counter++}
        }

        while (counter > 0){

            numbersArray.add(analysisString.substring(0, analysisString.indexOf('?')).toInt())
            analysisString = analysisString.removeRange(0, (analysisString.indexOf('?')+1))
            counter--

        }



        Log.d(TAG, "unmakeString: $analysisString")
        Log.d(TAG, "unmakeString2: $symbolArray")
        Log.d(TAG, "unmakeString3: $numbersArray")

    }
}