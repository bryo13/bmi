package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wei = findViewById<EditText>(R.id.edWeight)
        val hei = findViewById<EditText>(R.id.edHeight)
        val calc = findViewById<Button>(R.id.calc)


        calc.setOnClickListener {
            val weight = wei.text.toString()
            val height = hei.text.toString()
            if (validation(weight,height)) {
                val bmi = (weight.toFloat()/(height.toFloat()/100*height.toFloat()/100))
                val bmi2digits = String.format("%.2f",bmi).toFloat()
                displayResults(bmi2digits)
            }
        }
    }

   private fun displayResults(bmi:Float) {
       var res = findViewById<TextView>(R.id.tvRes)
       var explain = findViewById<TextView>(R.id.tvExplain)

       res.text = bmi.toString()
       var color = 0
       var resultText = ""

       when {
           bmi < 18.50 -> {
               resultText = "Underweight"
               color = R.color.underweight
           }

           bmi in 18.50..24.99 -> {
                resultText = "Healthy"
               color = R.color.normal
           }
           bmi in 25.00..29.99 -> {
               resultText = "Overweight"
               color = R.color.overweight
           }
           bmi > 30.00 -> {
               resultText = "Obese"
               color = R.color.obese
           }
       }
       explain.text = resultText
       //explain.setTextColor(/* color = */ ContextCompat.getColor(this,color))
   }

    private fun validation(weight:String?,height:String?) :Boolean {
        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_SHORT).show()
                return false
            }else-> {
                return true
            }
        }
    }
}

