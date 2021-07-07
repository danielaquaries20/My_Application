package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MyProfile : AppCompatActivity() {


    //TextView
    private lateinit var tvNama: TextView
    private lateinit var tvGenderSiswa: TextView
    private lateinit var tvAlamatSiswa: TextView
    private lateinit var tvSekolahSiswa: TextView
    private lateinit var tvHobiSiswa: TextView
    private lateinit var tvEmailSiswa: TextView
    private lateinit var tvBerapaKaliPause: TextView

    //Function Lifecycle
    private var penghitung = 0

    override fun onPause() {

        penghitung++
        tvBerapaKaliPause.text = "$penghitung Kali Pause"

        super.onPause()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_profile)


        //TextView
        tvNama = findViewById(R.id.tvNamaSiswa)
        tvGenderSiswa = findViewById(R.id.tvGenderSiswa)
        tvAlamatSiswa = findViewById(R.id.tvAlamatSiswa)
        tvSekolahSiswa = findViewById(R.id.tvSekolahSiswa)
        tvHobiSiswa = findViewById(R.id.tvHobiSiswa)
        tvEmailSiswa = findViewById(R.id.tvEmailSiswa)
        tvBerapaKaliPause = findViewById(R.id.tvBerapaKaliPause)





        //Input Siswa
        val siswa = Siswa()
        siswa.nama = "Daniel Aquaries Pratma"
        siswa.gender = "Laki - Laki"
        siswa.alamat = "Jenar Asri RT 07 RW 08, Ungaran Barat"
        siswa.sekolah = "SMK Negeri 11 Semarang"
        siswa.hobi = "Main Game, Mendengar Musik, Menonton Movie"
        siswa.email = "danielaquaries@gmail.com"


        tvNama.text = siswa.nama
        tvGenderSiswa.text = siswa.gender
        tvAlamatSiswa.text = siswa.alamat
        tvSekolahSiswa.text = siswa.sekolah
        tvHobiSiswa.text = siswa.hobi
        tvEmailSiswa.text = siswa.email


    }
}
