package com.example.myapplication

class Calculator(var valueOne: Double = 0.0, var valueTwo: Double = 0.0, var operationID: Int = 0) {

    var result: Int = 0

    init {

        when (operationID){

            1 -> result = (valueOne + valueTwo).toInt()



        }
    }



}