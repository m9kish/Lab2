package com.example.lab22

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    private lateinit var etA: EditText
    private lateinit var etB: EditText
    private lateinit var etC: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etA = findViewById(R.id.etA)
        etB = findViewById(R.id.etB)
        etC = findViewById(R.id.etC)
        btnCalculate = findViewById(R.id.btnCalculate)
        tvResult = findViewById(R.id.tvResult)

        btnCalculate.setOnClickListener {
            val a_s = etA.text.toString()
            val b_s = etB.text.toString()
            val c_s = etC.text.toString()

            if ((a_s.contains("[0-9]".toRegex())) and (b_s.contains("[0-9]".toRegex())) and (c_s.contains("[0-9]".toRegex())))    {
                val a = a_s.toDouble()
                val b = b_s.toDouble()
                val c = c_s.toDouble()

                val roots = solveQuadraticEquation(a, b, c)

                if ((a == 0.0) and (a == b) and (a == c)) {
                    tvResult.text = "При любом x уравнение будет верным"
                } else {
                    if ((a == 0.0) and (b != 0.0)){
                        tvResult.text = "x = ${-c / b}"
                    }
                    else{
                        if (roots != null) {
                            if (roots.first == roots.second) {
                                tvResult.text = "x1 = x2 = ${roots.first}"
                            } else {
                                if (roots.first.isNaN()) {
                                    tvResult.text = "Корней нет"
                                } else {
                                    tvResult.text = "x1 = ${roots.first}     x2 = ${roots.second}"
                                }
                            }
                        } else {
                            tvResult.text = "Корней нет"
                        }
                    }
                }
            }
            else
            {
                tvResult.text = "Введены не числа"
            }
        }
    }

    private fun solveQuadraticEquation(a: Double, b: Double, c: Double): Pair<Double, Double>? {
        val discriminant = b * b - 4 * a * c

        if (discriminant < 0) {
            return null
        }

        val x1 = (-b + sqrt(discriminant)) / (2 * a)
        val x2 = (-b - sqrt(discriminant)) / (2 * a)

        return Pair(x1, x2)
    }
}