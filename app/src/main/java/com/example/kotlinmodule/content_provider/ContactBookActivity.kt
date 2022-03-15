package com.example.kotlinmodule.content_provider

import android.Manifest
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.kotlinmodule.databinding.ActivityContactBookBinding

private lateinit var binding:ActivityContactBookBinding
class ContactBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityContactBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val permission=registerForActivityResult(
            ActivityResultContracts.RequestPermission(), ActivityResultCallback {
                if(it){
                    Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show()
                }
            }
        )
        askForPermission(permission)

        getContacts()
    }

    private fun getContacts() {

        val contentResolver=contentResolver
        val uri:Uri=ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val cursor=contentResolver.query(uri,null,null,null,null)
        Log.i("totalContact",cursor?.count.toString())
        if(cursor?.count!! >0){
            while (cursor.moveToNext()){
                val contactName=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val contactNumber=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                Log.i("nameNumber", "$contactName $contactNumber")
            }
        }

    }

    private fun askForPermission(permission: ActivityResultLauncher<String>) {
        permission.launch(Manifest.permission.READ_CONTACTS)
    }
}