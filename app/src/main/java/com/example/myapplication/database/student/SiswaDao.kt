package com.example.myapplication.database.student

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SiswaDao {
    @Insert
    fun insert(siswa: Siswa)

    @Query("SELECT * FROM Siswa")
    fun getAll(): LiveData<List<Siswa>>

    @Update
    fun update(siswa: Siswa)

    @Delete
    fun delete(siswa: Siswa)
}