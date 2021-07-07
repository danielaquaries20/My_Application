package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class  DetailProfileTemanSekelasActivity : AppCompatActivity() {

    //TextView
    private lateinit var textNamaTeman: TextView
    private lateinit var textSekolahTeman: TextView
    private lateinit var textGenderTeman: TextView

    //ImageView
    private lateinit var gambarProfile: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_profile_teman_sekelas)

        //TextView
        textNamaTeman = findViewById(R.id.tvNamaTemanSiswa)
        textSekolahTeman = findViewById(R.id.textSekolahTemanSiswa)
        textGenderTeman = findViewById(R.id.textGenderTemanSiswa)

        //ImageView
        gambarProfile = findViewById(R.id.ivGambarProfileTeman)

        val nama = intent.getStringExtra("NAMA")
        val sekolah = intent.getStringExtra("SEKOLAH")
        val gender = intent.getStringExtra("GENDER")
        val gambar = intent.getIntExtra("GAMBAR", 0)

        textNamaTeman.text = nama
        textSekolahTeman.text = sekolah
        textGenderTeman.text = gender
        gambarProfile.setImageResource(gambar)


    }
}