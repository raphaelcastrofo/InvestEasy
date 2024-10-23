package com.example.investeasy

import android.icu.text.NumberFormat
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import java.util.Locale



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val edtContribution = findViewById<TextInputEditText>(R.id.tie_contribution)
        val edtTime = findViewById<TextInputEditText>(R.id.tie_time)
        val edtInterest = findViewById<TextInputEditText>(R.id.tie_interest)
        val btnCalculate = findViewById<Button>(R.id.btn_calculate)
        val btcClear = findViewById<Button>(R.id.btn_clear)


        val tvResult = findViewById<TextView>(R.id.tv_result)
        val tvYield = findViewById<TextView>(R.id.tv_yielded_value)

        btnCalculate.setOnClickListener {

            val contributionStr: String = edtContribution.text.toString()
            val timeStr: String = edtTime.text.toString()
            val interestStr: String = edtInterest.text.toString()

            if (contributionStr == "" || timeStr == "") {
                //show mensage to the user
                Snackbar
                    .make(
                        edtContribution,
                        "fill in all the fields",
                        Snackbar.LENGTH_LONG
                    )
                    .show()
            } else {


                val contribution: Float = contributionStr.toFloat()
                val time: Float = timeStr.toFloat()
                val interest: Float = interestStr.toFloat()

                val totalContribution: Float = time * contribution
                val total : Float = totalContribution * interest

                val yield = total - totalContribution

                val formatter: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))

                tvResult.text = formatter.format(total)
                tvYield.text = formatter.format(yield)


            }
        }

        btcClear.setOnClickListener{
            contributionStr == ""
            edtTime == ""
            edtInterest = ""
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}

