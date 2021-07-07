package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.database.SharedPreferencesDataBase
import com.example.myapplication.databinding.ActivitySharedPreferencesBinding

class SharedPreferencesActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySharedPreferencesBinding

    private lateinit var theData: SharedPreferencesDataBase

    val inputName = MutableLiveData("")

    private var calonDisimpan: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        theData = SharedPreferencesDataBase(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_shared_preferences)
        binding.activity = this

        inputName.observe(this, {
            calonDisimpan = it
        })

        binding.nama = theData.getName()
    }
    fun saveName() {
        theData.saveName(calonDisimpan)
        binding.nama = calonDisimpan
    }
}