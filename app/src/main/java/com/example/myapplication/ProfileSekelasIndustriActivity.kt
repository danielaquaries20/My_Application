package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.AdapterREProfileSekelas
import com.example.myapplication.databinding.ActivityProfileSekelasIndustriBinding
import com.example.myapplication.model.TemanSekelas

class ProfileSekelasIndustriActivity : AppCompatActivity() {

    //RecyclerView
    private lateinit var recyclerTemanSekelas: RecyclerView

    val item:MutableList<TemanSekelas> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityProfileSekelasIndustriBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setContentView(R.layout.activity_profile_sekelas_industri)

//        //RecyclerView
//        recyclerTemanSekelas = findViewById(R.id.rvProfileSekelas)
//
//
        initData()
        binding.rvProfileSekelas.layoutManager = LinearLayoutManager(this)
        binding.rvProfileSekelas.adapter = AdapterREProfileSekelas(this, item)
//        recyclerTemanSekelas.layoutManager = LinearLayoutManager(this)
//        recyclerTemanSekelas.adapter = AdapterREProfileSekelas(this, item)
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