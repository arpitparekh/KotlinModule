package com.example.kotlinmodule.file_read_write

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import com.example.kotlinmodule.databinding.ActivityTextFileToExternalBinding
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader

private lateinit var binding:ActivityTextFileToExternalBinding
class TextFileToExternalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTextFileToExternalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(!isStorageAvailable()){
            Toast.makeText(this,"External Storage is not Available",Toast.LENGTH_SHORT).show()
        }
        binding.btnSendTextToExternal.setOnClickListener {
            val fileContent=binding.edtTextExternal.text.toString()
            if(fileContent != ""){
                val file= File(getExternalFilesDir("myFile.txt"),"MyFileDir")
                val fos=FileOutputStream(file)
                fos.write(fileContent.toByteArray())
                Toast.makeText(this,"File Written SuccessFully",Toast.LENGTH_SHORT).show()
                fos.close()
            }else{
                Toast.makeText(this,"Empty field",Toast.LENGTH_SHORT).show()
            }
            binding.edtTextExternal.setText("")

        }
        binding.btnGetTextFromExternal.setOnClickListener {
            val file= File(getExternalFilesDir("myFile.txt"),"MyFileDir")
        }
    }

    // check external storage is available or not
    private fun isStorageAvailable() : Boolean {
        val externalStorageState=Environment.getExternalStorageState()
        if(externalStorageState.equals(Environment.MEDIA_MOUNTED)){
            return true
        }
        return false
    }
}