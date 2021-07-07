package com.example.myapplication

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.myapplication.databinding.ActivityCameraAndGalleryBinding
import java.io.File

class CameraAndGalleryActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityCameraAndGalleryBinding

    private lateinit var photoFile: File
    private val photoName = "photo.jpg"
    private val TAG = "cameraapp"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraAndGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        photoFile = getPhotoFileUri(photoName)
    }


    private var activityLauncherCamera = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)
        binding.ivPictures.setImageBitmap(takenImage)
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
                binding.ivPictures.setImageBitmap(bitmap)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    private fun openCamera() {
        val fileProvider = FileProvider.getUriForFile(this, "com.example.myapplication", photoFile)

        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)
        }
        activityLauncherCamera.launch(takePictureIntent)

    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        activityLauncherGallery.launch(galleryIntent)
    }

    private fun getPhotoFileUri(fileName: String): File{
        val mediaStorageDir = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG)

        if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
            Log.d(TAG, "failed to create directory")
        }

        return File(mediaStorageDir.path + File.separator + fileName)
    }

    private fun checkPermissionCamera(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissionCamera() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 100)
    }

    private fun checkPermissionGallery(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissionGallery() {
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 110)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 100) {
            if (grantResults.all {it == PackageManager.PERMISSION_GRANTED}) {
                openCamera()
            } else {
                Toast.makeText(this, "User tidak memberikan izin", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == 110) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                openGallery()
            } else {
                Toast.makeText(this, "User tidak memberikan izin", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnCamera -> {
                if (checkPermissionCamera()) {
                    openCamera()
                } else {
                    requestPermissionCamera()
                }
            }

            binding.btnGallery -> {
                if (checkPermissionGallery()) {
                    openGallery()
                } else {
                    requestPermissionGallery()
                }
            }

        }
    }
}