package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    //Text View
    private lateinit var tvApps: TextView

    //Button
    private lateinit var buttonNext: Button
    private lateinit var buttonProfile: Button
    private lateinit var buttonKeLoopingGambar: Button
    private lateinit var buttonProfileSekelas: Button
    private lateinit var buttonCobaCoba: Button
    private lateinit var buttonProfileSekelasDataBinding: Button
    private lateinit var buttonSharedPreferences: Button
    private lateinit var buttonDaftarSiswa: Button
    private lateinit var buttonCameraAndGallery: Button

    //Switch
    private lateinit var switchLightNight: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Switch
        switchLightNight = findViewById(R.id.switchLightNightMode)

        switchLightNight.setOnClickListener {
            if (switchLightNight.isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        //Button
        buttonNext = findViewById(R.id.btnNext)
        buttonProfile = findViewById(R.id.btnProfile)
        buttonKeLoopingGambar = findViewById(R.id.btnLihatListGambar)
        buttonProfileSekelas = findViewById(R.id.btnProfileSekelas)
        buttonCobaCoba = findViewById(R.id.btnCobaCoba)
        buttonProfileSekelasDataBinding = findViewById(R.id.btnProfileSekelasDataBinding)
        buttonSharedPreferences = findViewById(R.id.btnSharedPreferences)
        buttonDaftarSiswa = findViewById(R.id.btnDaftarSiswa)
        buttonCameraAndGallery = findViewById(R.id.btnCameraAndGallery)

        buttonNext.setOnClickListener {
            val menuju = Intent(this, CobaCoba::class.java)
            startActivity(menuju)
        }

        buttonProfile.setOnClickListener {
            val letsGo = Intent(this, MyProfile::class.java)
            startActivity(letsGo)
        }

        buttonKeLoopingGambar.setOnClickListener {
            val menujuLoopGambar = Intent(this, BelajarKotlinMinggu7::class.java)
            startActivity(menujuLoopGambar)
        }

        buttonProfileSekelas.setOnClickListener {
            val menujuProfileSekelas = Intent(this, ProfileSekelasIndustriActivity::class.java)
            startActivity(menujuProfileSekelas)
        }

        buttonCobaCoba.setOnClickListener {
            val cobaCoba = Intent(this, Pertemuan9Activity::class.java)
            startActivity(cobaCoba)
        }

        buttonProfileSekelasDataBinding.setOnClickListener {
            val profileWithDataBind = Intent(this, ProfileTemanKeduaActivity::class.java)
            startActivity(profileWithDataBind)
        }

        buttonSharedPreferences.setOnClickListener {
            val keSharedPref = Intent(this, SharedPreferencesActivity::class.java)
            startActivity(keSharedPref)
        }

        buttonDaftarSiswa.setOnClickListener {
            val keDaftarSiswa = Intent(this, DaftarNamaSiswaActivity::class.java)
            startActivity(keDaftarSiswa)
        }

        buttonCameraAndGallery.setOnClickListener {
            val keCameraAndGallery = Intent(this, CameraAndGalleryActivity::class.java)
            startActivity(keCameraAndGallery)
        }

        //Text View
        tvApps = findViewById(R.id.textApps)

        tvApps.setOnClickListener {
            val maju = Intent(this@MainActivity, CobaCoba::class.java)
            startActivity(maju)
        }


    }

    fun menu(view: View) {}
}