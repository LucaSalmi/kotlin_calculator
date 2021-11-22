package com.example.myapplication

import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.util.*

class Calculator(val operationArray: MutableList<Int>, val symbolArray: MutableList<String>) {

    var result = ""

    init {

        stringTranslate()

    }

     private fun stringTranslate() {

        var temp = 0
        var value1 = 0
        var value2 = 0
        var opPos = 0

         while(operationArray.contains(0)){

            operationArray.removeAt(operationArray.indexOf(0))
        }

        while (symbolArray.contains("*")){

            opPos = symbolArray.indexOf("*")
            value1 = operationArray[opPos]
            value2 = operationArray[opPos+1]
            temp = value1*value2
            symbolArray.removeAt(opPos)
            operationArray.removeAt(opPos+1)
            operationArray.removeAt(opPos)
            operationArray.add(opPos, temp)
        }

        while (symbolArray.contains("/")){

            opPos = symbolArray.indexOf("/")
            value1 = operationArray[opPos]
            value2 = operationArray[opPos+1]
            temp = value1/value2
            symbolArray.removeAt(opPos)
            operationArray.removeAt(opPos+1)
            operationArray.removeAt(opPos)
            operationArray.add(opPos, temp)
        }

         while (symbolArray.contains("-")){

             opPos = symbolArray.indexOf("-")
             value1 = operationArray[opPos]
             value2 = operationArray[opPos+1]
             temp = value1-value2
             symbolArray.removeAt(opPos)
             operationArray.removeAt(opPos+1)
             operationArray.removeAt(opPos)
             operationArray.add(opPos, temp)
         }

        while (symbolArray.contains("+")){

            opPos = symbolArray.indexOf("+")
            value1 = operationArray[opPos]
            value2 = operationArray[opPos+1]
            temp = value1+value2
            symbolArray.removeAt(opPos)
            operationArray.removeAt(opPos+1)
            operationArray.removeAt(opPos)
            operationArray.add(opPos, temp)
        }

         result = operationArray[0].toString()

    }


}