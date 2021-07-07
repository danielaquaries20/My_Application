package com.example.myapplication.database.student

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Siswa(
    var nama: String,
    var sekolah: String,
    var gender: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
