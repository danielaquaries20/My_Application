package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout

class BelajarKotlinMinggu7 : AppCompatActivity() {

    //EditText
    private lateinit var editTextKetikJumlah: EditText

    //Button
    private lateinit var buttonTampilkan: Button

    //ImageView
    private lateinit var imageTampilanGambar: ImageView

    //Layout
    private lateinit var linearGambar: LinearLayout

    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_belajar_kotlin_minggu7)

        //EditText
        editTextKetikJumlah = findViewById(R.id.etJumlah)

        //Button
        buttonTampilkan = findViewById(R.id.btnTampilkanJumlahGambar)

        //ImageView
        imageTampilanGambar = findViewById(R.id.ivGambar)

        //Layout
        linearGambar = findViewById(R.id.linearLayoutGambar)



        buttonTampilkan.setOnClickListener {
            linearGambar.removeAllViews()

            val dataDariET = editTextKetikJumlah.text.toString().toInt()
            for (angka in 1..dataDariET) {
                val gambar = ImageView(this@BelajarKotlinMinggu7)
                gambar.layoutParams = LinearLayout.LayoutParams(200, 100)
                gambar.setImageResource(R.drawable.car5)
                linearGambar.addView(gambar)
            }
        }
    }
}