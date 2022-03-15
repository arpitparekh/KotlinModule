package com.example.kotlinmodule.firebase_crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlinmodule.databinding.ActivityAddEmployeeBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.FirebaseDatabase

private lateinit var binding:ActivityAddEmployeeBinding

class AddEmployeeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title="Add Employee Employee"
        binding= ActivityAddEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val key : String? =intent.getStringExtra("key")
        val employee = intent.getSerializableExtra("employee") as? Employee


        binding.edtFirstName.setText(employee?.firstName)
        binding.edtLastName.setText(employee?.lastName)
        binding.edtEmail.setText(employee?.email)
        binding.edtPhoneNumber.setText(employee?.phoneNumber)

        binding.btnSubmitFirebase.setOnClickListener {

            val database:FirebaseDatabase= FirebaseDatabase.getInstance()

            if(key==null){     // insert

                val firstName=binding.edtFirstName.text.toString()
                val lastName=binding.edtLastName.text.toString()
                val email=binding.edtEmail.text.toString()
                val phoneNumber=binding.edtPhoneNumber.text.toString()

                val myEmployee=Employee(firstName,lastName,email,phoneNumber)
                val reference=database.getReference("Employee").push()

                reference.setValue(myEmployee).addOnCompleteListener(OnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(this,"Data Added successFully",Toast.LENGTH_SHORT).show()
                        intent= Intent(this,EmployeeListActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,"Data Adding Failed",Toast.LENGTH_SHORT).show()
                    }
                })
            }
            else{     // update

                val firstName=binding.edtFirstName.text.toString()
                val lastName=binding.edtLastName.text.toString()
                val email=binding.edtEmail.text.toString()
                val phoneNumber=binding.edtPhoneNumber.text.toString()

                val myEmployee=Employee(firstName,lastName,email,phoneNumber)
                val reference=database.getReference("Employee").child(key)

                reference.setValue(myEmployee).addOnCompleteListener(OnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(this,"Data Updated successFully",Toast.LENGTH_SHORT).show()
                        intent= Intent(this,EmployeeListActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,"Data UpdateFailed",Toast.LENGTH_SHORT).show()
                    }
                })

            }


        }
    }
}