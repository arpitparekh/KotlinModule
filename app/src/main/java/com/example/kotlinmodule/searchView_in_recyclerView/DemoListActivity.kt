package com.example.kotlinmodule.searchView_in_recyclerView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinmodule.R
import com.example.kotlinmodule.databinding.ActivityDemoListActviityBinding
import java.util.*

private lateinit var binding:ActivityDemoListActviityBinding
private lateinit var adapter:DemoAdapter

class DemoListActivity : AppCompatActivity() {

    lateinit var displayList:MutableList<DemoData>
    lateinit var demoList: MutableList<DemoData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityDemoListActviityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //set layout for recyclerview

        val linearLayoutManager=LinearLayoutManager(this)
        binding.recyclerViewDemoList.layoutManager=linearLayoutManager

        demoList= mutableListOf()
        displayList= mutableListOf()

        demoList.add(DemoData("Arpit","arpitparekh9@gmail.com",25))
        demoList.add(DemoData("Pratik","pratikparekh9@gmail.com",30))
        demoList.add(DemoData("Shraddha","shraddhaparekh9@gmail.com",55))
        demoList.add(DemoData("Vimal","vimalparekh9@gmail.com",58))
        demoList.add(DemoData("Urvi","urviparekh9@gmail.com",25))

        displayList.addAll(demoList)

        adapter= DemoAdapter(displayList)

        binding.recyclerViewDemoList.adapter= adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu,menu)
        val menuItem=menu?.findItem(R.id.action_search)

        if(menuItem!=null){
            val searchView=menuItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    if(newText!!.isNotEmpty()){

                        displayList.clear()

                        val search = newText.lowercase(Locale.getDefault())

                        demoList.forEach{
                            if(it.name.lowercase(Locale.getDefault()).contains(search)){
                                displayList.add(it)
                            }
                        }
                        binding.recyclerViewDemoList.adapter!!.notifyDataSetChanged()

                    }
                    else{
                        displayList.clear()
                        displayList.addAll(demoList)
                        binding.recyclerViewDemoList.adapter!!.notifyDataSetChanged()
                    }
                    return true
                }

            })
        }

        return super.onCreateOptionsMenu(menu)
    }

}