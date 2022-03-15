package com.example.kotlinmodule.async_task_deprecated

import com.google.gson.annotations.SerializedName

data class Album(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null
)	
