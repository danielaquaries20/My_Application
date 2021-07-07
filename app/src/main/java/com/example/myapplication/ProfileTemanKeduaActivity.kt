package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.AdapterREProfileSekelas
import com.example.myapplication.databinding.ActivityProfileTemanKeduaBinding
import com.example.myapplication.model.TemanSekelas


class ProfileTemanKeduaActivity : AppCompatActivity() {

    lateinit var binding: ActivityProfileTemanKeduaBinding

    val item:MutableList<TemanSekelas> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_teman_kedua)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile_teman_kedua)

        initData()
        binding.rvProfileSekelasKedua.layoutManager = LinearLayoutManager(this)
        binding.rvProfileSekelasKedua.adapter = AdapterREProfileSekelas(this, item)

    }

    private fun initData() {
        val nama = resources.getStringArray(R.array.nama_teman)
        val sekolah = resources.getStringArray(R.array.sekolah_teman)
        val gender = resources.getStringArray(R.array.gender_teman)
        val gambar = resources.obtainTypedArray(R.array.gambar_profile)

        for (i in nama.indices){
            item.add(
                TemanSekelas(
                    nama[i],
                    sekolah[i],
                    gender[i],
                    gambar.getResourceId(i, 0)
                )
            )
        }
        gambar.recycle()
    }

}