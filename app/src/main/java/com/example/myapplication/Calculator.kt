package com.example.myapplication

import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.util.*

class Calculator(val operationArray: MutableList<String>) {

    var result = 0.0

    init {

        stringTranslate()

    }

    private fun stringTranslate() {

        var temp = 0
        var pos1 = 0
        var pos2 = 0
        var pos3 = 0

        while (operationArray.size > 1) {

            myloop@ for (i in 0..operationArray.size) {

                when (operationArray[i]) {

                    "*" -> {
                        temp =
                            (operationArray[i - 1]).toInt() * (operationArray[i - 1]).toInt()
                        pos1 = i+1
                        pos2 = i
                        pos3 = i-1
                        Log.d(TAG, "stringTranslate: ${temp.toString()}")

                        break@myloop
                    }

                }

            }

            operationArray.removeAt(pos1)
            operationArray.removeAt(pos2)
            operationArray.removeAt(pos3)
        }


    }


}