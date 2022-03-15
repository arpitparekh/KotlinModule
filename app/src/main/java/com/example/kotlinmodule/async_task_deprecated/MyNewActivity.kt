package com.example.kotlinmodule.async_task_deprecated

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Adapter
import android.widget.ArrayAdapter
import com.example.kotlinmodule.databinding.ActivityMyNewBinding
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.Executors

private lateinit var binding: ActivityMyNewBinding

private lateinit var adapter: ArrayAdapter<Album>
private lateinit var albumList: ArrayList<Album>
private lateinit var album: Album
private lateinit var jsonArray: JSONArray

class MyNewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyNewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchDataFromServer()
    }

    private fun fetchDataFromServer() {

        albumList=ArrayList()

        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        executor.execute {

            //just like do in background

            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://jsonplaceholder.typicode.com/albums/")
                .build()

            val response = client.newCall(request).execute()
            val resp : String = response.body!!.string()
            Log.i("hi", resp)

            handler.post {

                jsonArray = JSONArray(resp)

                for (i in 0 until jsonArray.length()) {

                    val jsonObject: JSONObject = jsonArray.getJSONObject(i)
                    val id = jsonObject.getInt("id")
                    val userId = jsonObject.getInt("userId")
                    val title = jsonObject.getString("title")

                    album = Album(id, title, userId)

                    albumList.add(album)
                }

                adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, albumList)
                binding.listViewAsyncTaskDeprecated.adapter = adapter

            }
        }




    }
}