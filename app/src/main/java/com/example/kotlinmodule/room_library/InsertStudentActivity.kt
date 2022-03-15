package com.example.kotlinmodule.room_library

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinmodule.databinding.ActivityInsertStudentBinding
import com.example.kotlinmodule.room_library.database.AppDatabase
import com.example.kotlinmodule.room_library.database.UtilityHelper

private lateinit var binding:ActivityInsertStudentBinding
private lateinit var student:Student
class InsertStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityInsertStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnInsert.setOnClickListener{


            val dao:StudentDao =UtilityHelper.getDatabase(this).studentDao()

            val id=binding.edtIdStudent.text.toString().toInt()
            val name=binding.edtNameStudent.text.toString()
            val address=binding.edtAddressStudent.text.toString()

            student=Student(id,name,address)
            dao.insertStudent(student)

            intent= Intent(this,StudentListActivity::class.java)
            startActivity(intent)
        }
    }
}