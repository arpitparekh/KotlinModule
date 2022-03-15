package com.example.kotlinmodule.room_library

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinmodule.R
import com.example.kotlinmodule.databinding.ActivityStudentListBinding
import com.example.kotlinmodule.databinding.DialogLayoutBinding
import com.example.kotlinmodule.room_library.adapter.StudentAdapter
import com.example.kotlinmodule.room_library.database.UtilityHelper

private lateinit var binding:ActivityStudentListBinding
private lateinit var linearLayoutManager:LinearLayoutManager
private lateinit var studentList:List<Student>
private lateinit var adaper:StudentAdapter
private lateinit var binding1:DialogLayoutBinding
private lateinit var dao:StudentDao

class StudentListActivity : AppCompatActivity(), StudentAdapter.OnStudentClickListener {

    override fun onResume() {
        super.onResume()
        fetchUserList()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        title="Student List"

        binding= ActivityStudentListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        linearLayoutManager= LinearLayoutManager(this)
        binding.recyclerViewStudent.layoutManager= linearLayoutManager

        fetchUserList()
    }

    private fun fetchUserList() {

        dao= UtilityHelper.getDatabase(this).studentDao()

        studentList=dao.getAllStudent()

        adaper= StudentAdapter(studentList,this)

        binding.recyclerViewStudent.adapter= adaper
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_item_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.action_add){
            intent= Intent(this,InsertStudentActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClickListener(position: Int) {
        val builder=AlertDialog.Builder(this)
        builder.setTitle("Select One")
        builder.setNegativeButton("Delete", DialogInterface.OnClickListener { dialog, which ->
            val student= studentList[position]
            val dao:StudentDao = UtilityHelper.getDatabase(this).studentDao()
            dao.deleteStudent(student)
            fetchUserList()

        })
        builder.setNeutralButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
        })

        builder.setPositiveButton("Update", DialogInterface.OnClickListener { dialog, which ->

            // dialog inside a dialog  //  inception

            val student = studentList[position]
            val builder1=AlertDialog.Builder(this)
            builder1.setTitle("Update the Detail")

            binding1= DialogLayoutBinding.inflate(layoutInflater)   // initialize dialog layout

            builder1.setView(binding1.root)                         // apply custom layout to dialog box

            binding1.edtIdDialog.setText(student.id.toString())
            binding1.edtNameDialog.setText(student.name.toString())
            binding1.edtAddressDialog.setText(student.address.toString())
            binding1.edtIdDialog.isEnabled=false

            builder1.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->

            val id= binding1.edtIdDialog.text.toString().toInt()
            val name= binding1.edtNameDialog.text.toString()
            val address=binding1.edtAddressDialog.text.toString()

            val student1=Student(id,name,address)
                dao.updateStudent(student1)
                fetchUserList()
            })
                builder1.setNeutralButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                }).create().show()

        }).create().show()
    }
}