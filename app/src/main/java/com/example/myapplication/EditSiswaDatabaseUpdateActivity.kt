package com.example.myapplication

import android.content.Intent
import android.graphics.ImageDecoder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.example.myapplication.database.MyDatabase
import com.example.myapplication.database.student.BitmapConverter
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
    var photoProfile = ""

    private var activityLauncherGallery = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        result.data?.data?.let {
            try {
                val bitmap = if (Build.VERSION.SDK_INT < 28) {
                    MediaStore.Images.Media.getBitmap(this.contentResolver, it)
                } else {
                    val source = ImageDecoder.createSource(this.contentResolver, it)
                    ImageDecoder.decodeBitmap(source)
                }
                binding.ivEditProfile.setImageBitmap(bitmap)
                photoProfile = BitmapConverter().bitmapToString(bitmap)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_siswa_database_update)

        idSiswa = intent.getIntExtra("id", 0)
        nama = intent.getStringExtra("nama") ?: ""
        sekolah = intent.getStringExtra("sekolah") ?: ""
        gender = intent.getStringExtra("gender") ?: ""
        photoProfile = intent.getStringExtra("photo") ?: ""

        binding.activity = this

        val bitmap = BitmapConverter().stringToBitmap(this, photoProfile)
        binding.ivEditProfile.setImageBitmap(bitmap)


        database = MyDatabase.getDatabase(this)
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.ivEditProfile->{
                val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityLauncherGallery.launch(galleryIntent)
            }
            binding.buttonUpdateSiswa -> {
                Log.d("tes data edit", "$nama $sekolah $gender $photoProfile")
                if (nama.isNotEmpty() && sekolah.isNotEmpty() && gender.isNotEmpty() && photoProfile.isNotEmpty()) {
                    val siswa = Siswa(nama, sekolah, gender, photoProfile).apply {
                        id = idSiswa
                    }

                    Executors.newSingleThreadExecutor().execute {
                        database.siswaDao().update(siswa)
                    }
                    val intent = Intent(this,DaftarNamaSiswaActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this, "Data berhasil diubah", Toast.LENGTH_LONG).show()
                    finish()
                }
            }

            binding.ivDelete -> {
                val siswa = Siswa(nama, sekolah, gender, "").apply {
                    id = idSiswa
                }

                Executors.newSingleThreadExecutor().execute {
                    database.siswaDao().delete(siswa)
                }
                val intent = Intent(this,DaftarNamaSiswaActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Data berhasil dihapus", Toast.LENGTH_LONG).show()
                finish()

            }

        }

    }
}