package com.example.myapplication

import android.content.ContentValues
import android.util.Log
import com.example.myapplication.databinding.ActivityMainBinding

object StringNumberManager {

    fun unmakeString(operationString: String, symbolArray: MutableList<String>, numbersArray: MutableList<Int>) {

        var counter = 0
        var analysisString = operationString

        for (c in analysisString) {

            when (c) {

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

        for (c in analysisString) {

            if (c == '?') {
                counter++
            }
        }

        while (counter > 0) {

            numbersArray.add(analysisString.substring(0, analysisString.indexOf('?')).toInt())
            analysisString = analysisString.removeRange(0, (analysisString.indexOf('?') + 1))
            counter--

        }

    }

    fun restoreFromMemory(a: String): String {

        return a.substring(a.indexOf('=')+1, a.lastIndex+1)

    }
}