package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder

class CobaCoba : AppCompatActivity() {

    private lateinit var textPengetikan: TextView
    private lateinit var textHasil: TextView

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button
    private lateinit var button0: Button

    private lateinit var buttonTambah: Button
    private lateinit var buttonKurang: Button
    private lateinit var buttonKali: Button
    private lateinit var buttonBagi: Button
    private lateinit var buttonClear: Button
    private lateinit var buttonDelete: Button
    private lateinit var buttonKurungBuka: Button
    private lateinit var buttonKurungTutup: Button
    private lateinit var buttonTitik: Button
    private lateinit var buttonSamaDengan: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coba_coba)

        textPengetikan = findViewById(R.id.tvTulis)
        textHasil = findViewById(R.id.tvHasil)

        button0 = findViewById(R.id.btnNol)
        button1 = findViewById(R.id.btnSatu)
        button2 = findViewById(R.id.btnDua)
        button3 = findViewById(R.id.btnTiga)
        button4 = findViewById(R.id.btnEmpat)
        button5 = findViewById(R.id.btnLima)
        button6 = findViewById(R.id.btnEnam)
        button7 = findViewById(R.id.btnTujuh)
        button8 = findViewById(R.id.btnDelapan)
        button9 = findViewById(R.id.btnSembilan)

        buttonTambah = findViewById(R.id.btnTambah)
        buttonKurang = findViewById(R.id.btnKurang)
        buttonKali = findViewById(R.id.btnKali)
        buttonBagi = findViewById(R.id.btnBagi)
        buttonKurungBuka = findViewById(R.id.btnKurungBuka)
        buttonKurungTutup = findViewById(R.id.btnKurungTutup)
        buttonClear = findViewById(R.id.btnClear)
        buttonTitik = findViewById(R.id.btnTitik)
        buttonDelete = findViewById(R.id.btnDelete)
        buttonSamaDengan = findViewById(R.id.btnHasil)

        button1.setOnClickListener { saatPengetikan("1", true) }
        button2.setOnClickListener { saatPengetikan("2", true) }
        button3.setOnClickListener { saatPengetikan("3", true) }
        button4.setOnClickListener { saatPengetikan("4", true) }
        button5.setOnClickListener { saatPengetikan("5", true) }
        button6.setOnClickListener { saatPengetikan("6", true) }
        button7.setOnClickListener { saatPengetikan("7", true) }
        button8.setOnClickListener { saatPengetikan("8", true) }
        button9.setOnClickListener { saatPengetikan("9", true) }
        button0.setOnClickListener { saatPengetikan("0", true) }
        buttonTitik.setOnClickListener { saatPengetikan(".", true) }


        buttonTambah.setOnClickListener { saatPengetikan("+", false) }
        buttonKurang.setOnClickListener { saatPengetikan("-", false) }
        buttonKali.setOnClickListener { saatPengetikan("*", false) }
        buttonBagi.setOnClickListener { saatPengetikan("/", false) }
        buttonKurungBuka.setOnClickListener { saatPengetikan("(", false) }
        buttonKurungTutup.setOnClickListener { saatPengetikan(")", false) }


        buttonClear.setOnClickListener {
            textPengetikan.text = ""
            textHasil.text = ""
        }


        buttonDelete.setOnClickListener {
            val ketikkan = textPengetikan.text.toString()

            if (ketikkan.isNotEmpty()) {
                textPengetikan.text = ketikkan.substring(0, ketikkan.length - 1)
            }
            textHasil.text = ""
        }

        buttonSamaDengan.setOnClickListener {
            try {
                val expression = ExpressionBuilder(textPengetikan.text.toString()).build()
                val hasil = expression.evaluate()
                val hasilAkhir = hasil.toLong()

                if (hasil == hasilAkhir.toDouble())
                    textHasil.text = hasilAkhir.toString()
                else
                    textHasil.text = hasil.toString()

            } catch (e: Exception) {
                Log.d("Exception", "message : ${e.message}")
            }
        }


    }

    fun saatPengetikan(string: String, saatClear: Boolean) {

        if (textHasil.text.isNotEmpty()) {
            textPengetikan.text = ""
        }

        if (saatClear) {
            textHasil.text = ""
            textPengetikan.append(string)
        } else {
            textPengetikan.append(textHasil.text)
            textPengetikan.append(string)
            textHasil.text = ""
        }

    }
}