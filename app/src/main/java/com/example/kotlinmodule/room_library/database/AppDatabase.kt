package com.example.kotlinmodule.room_library.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlinmodule.room_library.Student
import com.example.kotlinmodule.room_library.StudentDao

@Database(entities = arrayOf(Student::class),version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao():StudentDao
}