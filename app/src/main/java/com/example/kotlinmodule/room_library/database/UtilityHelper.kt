package com.example.kotlinmodule.room_library.database

import android.content.Context
import androidx.room.Room

class UtilityHelper {

    companion object{
        fun getDatabase(context:Context): AppDatabase {
            val db:AppDatabase = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "Student Database"
            ).allowMainThreadQueries().build()
            return db
        }

    }
}