package com.example.kotlinmodule.room_library

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Student(
    @PrimaryKey
    val id:Int,
    @ColumnInfo(name="name")
    val name:String?,
    @ColumnInfo(name="age")
    val address:String?):Serializable