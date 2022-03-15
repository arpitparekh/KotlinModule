package com.example.kotlinmodule.room_library

import androidx.room.*

@Dao
interface StudentDao {

    @Insert
    fun insertStudent(student:Student)

    @Update
    fun updateStudent(student:Student)

    @Delete
    fun deleteStudent(student:Student)

    @Query("SELECT * FROM Student")
    fun getAllStudent():List<Student>


}