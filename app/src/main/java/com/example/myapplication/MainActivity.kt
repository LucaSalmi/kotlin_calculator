package com.example.myapplication

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CustomAdapter.onClick {


    var resultString = ""
    var operationString = ""
    private val numbersArray = mutableListOf<Int>()
    private val symbolArray = mutableListOf<String>()
    private val data = ArrayList<ItemsViewModel>()
    var isSymbol = false
    var erase = false
    var isResult = false

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFieldsAndListeners()

    }

    private fun setFieldsAndListeners() {

        binding.memoryLayout.layoutManager = LinearLayoutManager(this)
        val adapter = CustomAdapter(data, this)
        binding.memoryLayout.adapter = adapter



        binding.num1.setOnClickListener {

            buttonAction(1)
        }
        binding.num2.setOnClickListener {

            buttonAction(2)
        }
        binding.num3.setOnClickListener {

            buttonAction(3)
        }
        binding.num4.setOnClickListener {

            buttonAction(4)
        }
        binding.num5.setOnClickListener {

            buttonAction(5)
        }
        binding.num6.setOnClickListener {

            buttonAction(6)
        }
        binding.num7.setOnClickListener {

            buttonAction(7)
        }
        binding.num8.setOnClickListener {

            buttonAction(8)
        }
        binding.num9.setOnClickListener {

            buttonAction(9)
        }
        binding.num0.setOnClickListener {

            buttonAction(0)
        }
        binding.plusBtn.setOnClickListener {

            buttonAction(10)
        }
        binding.minusBtn.setOnClickListener {

            buttonAction(11)
        }
        binding.multiplyBtn.setOnClickListener {

            buttonAction(12)
        }
        binding.divisionBtn.setOnClickListener {

            buttonAction(13)
        }

        binding.clear.setOnClickListener {

            operationString = ""
            updateFields()
        }

        binding.erase.setOnClickListener { buttonAction(15) }

        binding.resultBtn.setOnClickListener {

            resultBtnAction()
            adapter.notifyDataSetChanged()
        }
    }

    /**
     * manages what the result button does when pressed.
     * it checks if the user is trying to use 0 in an operation, if the operation ends with a symbol instead of a number
     *
      */
    private fun resultBtnAction() {

        if (endsWithSymb()) {

            Toast.makeText(this, getString(R.string.warning_not_calc), Toast.LENGTH_SHORT).show()
            return

        } else "$operationString?"


        StringNumberManager.unmakeString(operationString, symbolArray, numbersArray)

        if (checkForZeroes()) {

            Toast.makeText(this, getString(R.string.warning_zero), Toast.LENGTH_SHORT).show()
            arrayCleaner()
            return

        }

        if (checkIfEmpty()) {

            var obj = sendToCalc()
            resultString = obj.result
            buttonAction(14)
            updateFields()
            arrayCleaner()

        } else Toast.makeText(this, getString(R.string.warning_not_calc), Toast.LENGTH_SHORT).show()
    }

    private fun checkForZeroes(): Boolean {

        return numbersArray.contains(0)

    }

    private fun checkIfEmpty(): Boolean {

        return numbersArray.size >= 2
    }

    private fun arrayCleaner() {

        numbersArray.clear()
        symbolArray.clear()

    }

    private fun endsWithSymb(): Boolean {

        return (operationString.endsWith('+') || operationString.endsWith('-') || operationString.endsWith(
            '*'
        ) || operationString.endsWith('/'))
    }

    private fun buttonAction(symbId: Int) {

        when (symbId) {

            0 -> stringMaker("0")
            1 -> stringMaker("1")
            2 -> stringMaker("2")
            3 -> stringMaker("3")
            4 -> stringMaker("4")
            5 -> stringMaker("5")
            6 -> stringMaker("6")
            7 -> stringMaker("7")
            8 -> stringMaker("8")
            9 -> stringMaker("9")
            10 -> {
                changeBool(0)
                stringMaker("+")
            }
            11 -> {
                changeBool(0)
                stringMaker("-")
            }
            12 -> {
                changeBool(0)
                stringMaker("*")
            }
            13 -> {
                changeBool(0)
                stringMaker("/")
            }
            14 -> {
                changeBool(2)
                stringMaker(resultString)
            }
            15 -> {
                changeBool(1)
                erase()
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

    private fun erase() {

        operationString = operationString.dropLast(1)
        changeBool(1)
        updateFields()
    }

    private fun addToMemory(temp: String) {

        data.add(ItemsViewModel("$operationString=$temp"))

    }

    /**
     * modifies the string showed to the user in the operation field of the app
     */
    private fun stringMaker(temp: String) {


        operationString = when {

            isSymbol -> {

                changeBool(0)

                if (endsWithSymb()) {

                    operationString = operationString.dropLast(1)

                }
                "$operationString$temp"

            }
            isResult -> {

                changeBool(2)
                addToMemory(temp)
                temp

            }
            else -> {

                "$operationString$temp"
            }
        }

        updateFields()
    }

    private fun updateFields() {

        binding.operationView.text = operationString

    }

    override fun onItemClick(operation: ItemsViewModel, position: Int) {
        super.onItemClick(operation, position)

        operationString = StringNumberManager.restoreFromMemory(operation.text)
        updateFields()

    }

}