package com.example.kotlinmodule.realm_kotlin

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.example.kotlinmodule.R
import com.example.kotlinmodule.databinding.ActivityRealmListBinding
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults

private lateinit var binding:ActivityRealmListBinding
private lateinit var realm:Realm
private lateinit var carsList: RealmResults<Cars>
private lateinit var cars:Cars


class RealmListActivity : AppCompatActivity(), AdapterView.OnItemClickListener {

    override fun onResume() {
        super.onResume()

        getAllCars()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title="Realm List Activity"
        binding= ActivityRealmListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Realm.init(this)
        val configuration=RealmConfiguration.Builder()
            .allowWritesOnUiThread(true)
            .name(getString(R.string.app_name))
            .build()

        realm=Realm.getInstance(configuration)

        Log.i("path", realm.path)          // for path

        getAllCars()

        binding.listViewRealm.onItemClickListener = this

    }

    private fun getAllCars() {
        carsList=realm.where(Cars::class.java).findAll()
        val adapter=ArrayAdapter<Cars>(this,android.R.layout.simple_list_item_1, carsList)
        binding.listViewRealm.adapter=adapter
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_item_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId==R.id.action_add){
            intent= Intent(this,AddDataIntoRealm::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        showAlertDialog(position)
    }

    private fun showAlertDialog(position: Int) {

        AlertDialog.Builder(this)
            .setTitle("Choose One")
            .setIcon(R.mipmap.ic_launcher)
            .setPositiveButton("Edit", DialogInterface.OnClickListener { dialog, which ->
                cars= carsList[position]!!
                editCars(cars,cars.id)
            })
            .setNegativeButton("Delete", DialogInterface.OnClickListener { dialog, which ->
                cars= carsList[position]!!
                deleteCars(cars.id)
            })
            .setNeutralButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            }).create().show()
    }

    private fun editCars(cars: Cars, id: Int) {
        intent = Intent(this,AddDataIntoRealm::class.java)
        intent.putExtra("cars",cars)
        intent.putExtra("id",id)
        startActivity(intent)
    }

    private fun deleteCars(id: Int) {
        realm.executeTransaction(Realm.Transaction {
            cars= it.where(Cars::class.java).equalTo("id",id).findFirst()!!
            cars.deleteFromRealm()
        })
        getAllCars()
    }
}