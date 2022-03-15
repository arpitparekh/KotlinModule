package com.example.kotlinmodule.dataBinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/* equals() / hashCode() pair

toString() of the form "User(name=John, age=42)"

componentN() functions corresponding to the properties in their order of declaration.

copy() function */

data class MyData(
    val id:Int,
    val name:String,
    val address:String,
    val isOnline:Boolean,
    val imageUrl:String
) {
    companion object{
        const val petName="Rocky"
        @JvmStatic
        @BindingAdapter("android:imageUrl")
        fun loadImage(imageView:ImageView,imageUrl: String?):Unit{          //  use of glide
            Glide.with(imageView.context)
                .load(imageUrl)
                .into(imageView)
        }
    }


}