package com.example.kotlinmodule.firebase_crud

import java.io.Serializable

public data class Employee(
    var firstName:String="",
    var lastName:String="",
    var email:String="",
    val phoneNumber:String=""
) : Serializable {
}