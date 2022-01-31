package com.example.myapplication

object StringNumberManager {

    /**
     * this method takes the string with the whole operation and extracts the operation symbols and numbers and puts them in two different arrays
     */
    fun unmakeString(
        operationString: String,
        symbolArray: MutableList<String>,
        numbersArray: MutableList<Int>
    ) {





//this loop fills the String array that contains the operation symbols in the order they appear in the string
        var counter = 0
        var operationString = operationString

        for (c in operationString) {

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
//this replaces all symbols in the string with ?
        for (c in operationString){

            if (c == '+'|| c == '-' || c == '*' || c == '/'){

                operationString = operationString.replace('+', '?').replace('-', '?').replace('*', '?').replace('/', '?')
                counter++

            }
        }

        operationString = "$operationString?"
        counter++

// this loop finds the numbers in the string, converts them to Int and puts them in an array in the original order
        while (counter > 0) {

            numbersArray.add(operationString.substring(0, operationString.indexOf('?')).toInt())
            operationString = operationString.removeRange(0, (operationString.indexOf('?') + 1))
            counter--

        }

    }

    fun restoreFromMemory(a: String): String {

        return a.substring(a.indexOf('=') + 1, a.lastIndex + 1)

    }
}