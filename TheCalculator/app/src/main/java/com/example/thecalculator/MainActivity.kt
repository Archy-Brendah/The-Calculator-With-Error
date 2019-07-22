package com.example.thecalculator

import android.content.Context
import android.content.Intent
import android.icu.util.IslamicCalendar

//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import  kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.calc.*
import kotlinx.android.synthetic.main.ticket.view.*


open class MainActivity : AppCompatActivity() {
    var listNotes=ArrayList<myCalc>()
    var Calculations:Double?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        history.setOnClickListener {
                                    val intent = Intent(this, calc::class.java)
            // start your next activity
            startActivity(intent)



            Toast.makeText(this,"onCreate", Toast.LENGTH_LONG).show()
//        h
            //Load from DB


        LoadQuery("%")

//


        }
    override  fun onResume() {
        super.onResume()
        LoadQuery("%")
        Toast.makeText(this,"onResume",Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this,"onStart",Toast.LENGTH_LONG).show()
    }

    override fun onPause() {
        super.onPause()

        Toast.makeText(this,"onPause",Toast.LENGTH_LONG).show()
    }

    override fun onStop() {
        super.onStop()

        Toast.makeText(this,"onStop",Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()

        Toast.makeText(this,"onDestroy",Toast.LENGTH_LONG).show()
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this,"onRestart",Toast.LENGTH_LONG).show()
    }

    fun LoadQuery(title:String){


        var dbManager=DbManager(this)

        var myNotesAdapter= MyNotesAdpater(this, listNotes)
        lvCalc.adapter=myNotesAdapter


    }

    inner class  MyNotesAdpater: BaseAdapter {
        var listNotesAdpater=ArrayList<myCalc>()
        var context: Context?=null
        constructor(context:Context, listNotesAdpater:ArrayList<myCalc>):super(){
            this.listNotesAdpater=listNotesAdpater
            this.context=context
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

            var myView=layoutInflater.inflate(R.layout.ticket,null)
            var myNote=listNotesAdpater[p0]


            myView.etText.text= myNote.nodeCalculations

            return myView
        }

        override fun getItem(p0: Int): Any {
            return listNotesAdpater[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {

            return listNotesAdpater.size

        }



    }





    fun buNumberEvent(view:View){
        if(isNewOp){
            etShowNumber.setText("")
        }
        isNewOp=false

        val buSelect= view as Button
        var buClickValue:String=etShowNumber.text.toString()
        when(buSelect.id){
            bu0.id->{
                buClickValue+="0"

            }
            bu1.id->{
                buClickValue+="1"
            }
            bu2.id->{
                buClickValue+="2"
            }
            bu3.id->{
                buClickValue+="3"
            }
            bu4.id->{
                buClickValue+="4"
            }
            bu5.id->{
                buClickValue+="5"
            }
            bu6.id->{
                buClickValue+="6"
            }
            bu7.id->{
                buClickValue+="7"
            }
            bu8.id->{
                buClickValue+="8"
            }
            bu9.id->{
                buClickValue+="9"
            }
            buDot.id->{
                //TODO: prevent adding morve than 1 dot
                buClickValue+="."
            }
            buPlusMins.id->{
                buClickValue= "-"+ buClickValue
            }
        }
        etShowNumber.setText(buClickValue)
    }


    var op="*"
    var oldNumber=""
    var isNewOp=true
    fun buOpEevent(view:View){

        val buSelect= view as Button
        when(buSelect.id) {
            buMul.id -> {

                op="*"


            }
            buDiv.id -> {

                op="/"
            }
            buSub.id -> {


                op="-"
            }
            buSum.id -> {

                op="+"
            }

        }
        oldNumber=etShowNumber.text.toString()
        isNewOp=true
    }

    fun buEqualEvent(view:View){
        val newNumber=etShowNumber.text.toString()
        var finalNumber:Double?=null

        when(op){

            "*"->{

                finalNumber=  oldNumber.toDouble() *newNumber.toDouble()
                Calculations=finalNumber

            }
            "/"->{
                finalNumber=  oldNumber.toDouble() /newNumber.toDouble()
                Calculations=finalNumber
            }
            "+"->{
                finalNumber=  oldNumber.toDouble()+newNumber.toDouble()
                Calculations=finalNumber
            }
            "-"->{
                finalNumber=  oldNumber.toDouble() *newNumber.toDouble()
                Calculations=finalNumber
            }
        }
        etShowNumber.setText(finalNumber.toString())
        isNewOp=true
    }

    fun buPercent(view:View){
        val number:Double=etShowNumber.text.toString().toDouble()/100
        Calculations=number

        etShowNumber.setText(number.toString())
        isNewOp=true

    }

    fun buClean(view:View){
        etShowNumber.setText("0")
        isNewOp=true
    }
}

