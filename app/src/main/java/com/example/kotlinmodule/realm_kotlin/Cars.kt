package com.example.kotlinmodule.realm_kotlin

import android.os.Parcelable
import io.realm.RealmObject
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
open class Cars(
    var  id:Int=0,
    var carName:String="",
    var brand:String="",
    var price:Int=0,
) : RealmObject(), Parcelable {

}