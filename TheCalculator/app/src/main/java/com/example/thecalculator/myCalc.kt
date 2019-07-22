package com.example.thecalculator
class myCalc:MainActivity {
    var nodeID: Int? = null
    var nodeCalculations: String? = Calculations.toString()


    constructor(nodeID: Int, nodeCalculations: String) {
        this.nodeID = nodeID
        this.nodeCalculations = nodeCalculations
    }

}