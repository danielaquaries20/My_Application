package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.myapplication.database.MyDatabase
import com.example.myapplication.database.student.Siswa
import com.example.myapplication.databinding.ActivityEditSiswaDatabaseUpdateBinding
import com.example.myapplication.databinding.ActivityTambahSiswaBinding
import java.util.concurrent.Executors

class EditSiswaDatabaseUpdateActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityEditSiswaDatabaseUpdateBinding

    private lateinit var database: MyDatabase

    var idSiswa = 0
    var nama = ""
    var sekolah = ""
    var gender = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_siswa_database_update)

        idSiswa = intent.getIntExtra("id", 0)
        nama = intent.getStringExtra("nama") ?: ""
        sekolah = intent.getStringExtra("sekolah") ?: ""
        gender = intent.getStringExtra("gender") ?: ""

        binding.activity = this


        database = MyDatabase.getDatabase(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.buttonUpdateSiswa -> {
//                Log.d("tes data", "$nama $sekolah $gender")
                if (nama.isNotEmpty() && sekolah.isNotEmpty() && gender.isNotEmpty()) {
                    val siswa = Siswa(nama, sekolah, gender).apply {
                        id = idSiswa
                    }

                    Executors.newSingleThreadExecutor().execute {
                        database.siswaDao().update(siswa)
                    }

                    Toast.makeText(this, "Data berhasil diubah", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }

            binding.ivDelete -> {
                val siswa = Siswa(nama, sekolah, gender).apply {
                    id = idSiswa
                }

                Executors.newSingleThreadExecutor().execute {
                    database.siswaDao().delete(siswa)
                }

                Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
                finish()

            }

        }

    }
}