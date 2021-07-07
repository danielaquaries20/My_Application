package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityPertemuan9Binding

class Pertemuan9Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPertemuan9Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.iniTombol.setOnClickListener {
            val maju = Intent(this, SegalaCobaCobaActivity::class.java)
            startActivity(maju)
        }

    }
}