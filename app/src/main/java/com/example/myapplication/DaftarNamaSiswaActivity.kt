package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.myapplication.adapter.AdapterRETambahSiswa
import com.example.myapplication.database.MyDatabase
import com.example.myapplication.databinding.ActivityDaftarNamaSiswaBinding

class DaftarNamaSiswaActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var binding: ActivityDaftarNamaSiswaBinding

    private lateinit var database: MyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_daftar_nama_siswa)

        database = MyDatabase.getDatabase(this)

        database.siswaDao().getAll().observe(this, {
            binding.adapter = AdapterRETambahSiswa(it) {
                val intent = Intent(this, EditSiswaDatabaseUpdateActivity::class.java).apply {
                    putExtra("id", it.id)
                    putExtra("nama", it.nama)
                    putExtra("sekolah", it.sekolah)
                    putExtra("gender", it.gender)
                    putExtra("photo", it.photoProfile)
                }
                startActivity(intent)
            }
        })
    }

    override fun onClick(view: View?) {
        when(view) {
            binding.buttonTambahSiswa -> {
                val intent = Intent(this, TambahSiswaActivity::class.java)
                startActivity(intent)
            }
        }

    }
}