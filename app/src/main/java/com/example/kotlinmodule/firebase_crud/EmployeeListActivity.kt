package com.example.kotlinmodule.firebase_crud

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinmodule.R
import com.example.kotlinmodule.databinding.ActivityEmployeeListBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

private lateinit var binding:ActivityEmployeeListBinding
private lateinit var employeeList: MutableList<HashMap<String,Employee>>
private lateinit var adapter:EmployeeAdapter

class EmployeeListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title="Employee List"
        binding= ActivityEmployeeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val linearLayoutManager=LinearLayoutManager(this)
        binding.recyclerViewFirebase.layoutManager=linearLayoutManager

        FirebaseApp.initializeApp(this)
        fetchEmployeeData()
    }

    private fun fetchEmployeeData() {

        val database=FirebaseDatabase.getInstance()
        val reference=database.getReference("Employee")

        reference.addValueEventListener(object:ValueEventListener,

            EmployeeAdapter.OnEmployeeClickListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                val iterable=snapshot.children
                employeeList= mutableListOf()
                for(dataSnapshot:DataSnapshot in iterable){
                    val key=dataSnapshot.key
                    val employee=dataSnapshot.getValue(Employee::class.java)
                    val hashMap:HashMap<String,Employee> = HashMap()
                    if(key != null && employee!=null){
                        hashMap[key] = employee
                        employeeList.add(hashMap)
                    }
                    Log.i("employee", employeeList.toString())
                    adapter=EmployeeAdapter(employeeList,this)
                    binding.recyclerViewFirebase.adapter= adapter
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onEmployeeClick(key: String, employee: Employee) {

                AlertDialog.Builder(this@EmployeeListActivity)
                    .setTitle("Choose One")
                    .setPositiveButton("Update", DialogInterface.OnClickListener { dialog, which ->
                        //update
                        intent=Intent(this@EmployeeListActivity,AddEmployeeActivity::class.java)
                        intent.putExtra("key",key)
                        intent.putExtra("employee",employee)
                        startActivity(intent)

                    }).setNegativeButton("Delete", DialogInterface.OnClickListener { dialog, which ->
                        //delete

                        deleteEmployee(key)
                        if(employeeList.size==1){
                            refreshActivity()
                        }


                    }).setNeutralButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                        dialog.dismiss()
                    }).create().show()
            }
        })
    }
    private fun refreshActivity() {
        intent=intent
        finish()
        startActivity(intent)
    }

    private fun deleteEmployee(key:String) {

        val database=FirebaseDatabase.getInstance()
        val reference=database.getReference("Employee").child(key)
        reference.removeValue().addOnCompleteListener(OnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(applicationContext,"Delete SuccessFull",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext,"Delete UnSuccessFull",Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_item_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.action_add){
            intent= Intent(this,AddEmployeeActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}