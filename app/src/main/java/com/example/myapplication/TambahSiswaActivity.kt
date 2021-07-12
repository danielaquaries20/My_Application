package com.example.myapplication

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.myapplication.database.MyDatabase
import com.example.myapplication.database.student.BitmapConverter
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
    var photoProfile = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tambah_siswa)
        binding.activity = this

        database = MyDatabase.getDatabase(this)
    }

    private var activityLauncherGallery = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        result.data?.data?.let {
            try {
                val bitmap = if (Build.VERSION.SDK_INT < 28) {
                    MediaStore.Images.Media.getBitmap(this.contentResolver, it)
                } else {
                    val source  = ImageDecoder.createSource(this.contentResolver, it)
                    ImageDecoder.decodeBitmap(source)
                }
                binding.ivProfile.setImageBitmap(bitmap)
                photoProfile = BitmapConverter().bitmapToString(bitmap)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        activityLauncherGallery.launch(galleryIntent)
    }


    private fun checkPermissionGallery(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissionGallery() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
            110
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 110) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                openGallery()
            } else {
                Toast.makeText(this, "User tidak memberikan izin", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onClick(view: View?) {
        when (view) {
            binding.buttonSimpanSiswa -> {
                Log.d("tes data tambah", "$nama $sekolah $gender $photoProfile")
                if (nama.isNotEmpty() && sekolah.isNotEmpty() && gender.isNotEmpty() && photoProfile.isNotEmpty()) {
                    val siswa = Siswa(nama, sekolah, gender, photoProfile)

                    Executors.newSingleThreadExecutor().execute {
                        database.siswaDao().insert(siswa)
                    }
                    val intent = Intent(this,DaftarNamaSiswaActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_LONG).show()
                    finish()
                }
            }

            binding.ivProfile -> {
                if (checkPermissionGallery()) {
                    openGallery()
                } else {
                    requestPermissionGallery()
                }
            }

        }
    }
}