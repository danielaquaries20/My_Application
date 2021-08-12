package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.myapplication.database.MyDatabase
import com.example.myapplication.database.student.Siswa
import com.example.myapplication.databinding.ActivityTambahSiswaBinding
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class TambahSiswaActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityTambahSiswaBinding

    private lateinit var database: MyDatabase

    var nama = ""
    var sekolah = ""
    var gender = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tambah_siswa)
        binding.activity = this

        database = MyDatabase.getDatabase(this)

    }

    override fun onClick(view: View?) {
        when (view) {
            binding.buttonSimpanSiswa -> {
//                Log.d("tes data", "$nama $sekolah $gender")
                if (nama.isNotEmpty() && sekolah.isNotEmpty() && gender.isNotEmpty()) {
                    val siswa = Siswa(nama, sekolah, gender)

                    Executors.newSingleThreadExecutor().execute {
                        database.siswaDao().insert(siswa)
                    }

                    Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}