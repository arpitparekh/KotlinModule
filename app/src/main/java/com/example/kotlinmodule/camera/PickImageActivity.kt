package com.example.kotlinmodule.camera

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.kotlinmodule.databinding.ActivityPickImageBinding
import java.io.ByteArrayOutputStream

private lateinit var binding:ActivityPickImageBinding
class PickImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPickImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //////////////////////  check Permission registerForActivityResult   //////////////

        // we can also check multiple permission in registerForActivityResult

        val permission=registerForActivityResult(
            ActivityResultContracts.RequestPermission(), ActivityResultCallback {
                if(it){
                    Toast.makeText(this,"Permission is Granted",Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(this,"Permission is Denied",Toast.LENGTH_SHORT).show()
                }
            }
        )

        checkForPermission(permission)

        /////////////////////   Open Camera registerForActivityResult   ///////////////////

        val openCamera=registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(), ActivityResultCallback {
                if(it.resultCode== RESULT_OK && it.data!=null){
                    val bundle: Bundle? = it.data!!.extras
                    val bitmap:Bitmap= bundle?.get("data") as Bitmap

                    binding.imageViewPickImage.setImageBitmap(bitmap)

                }
            }
        )

        /////////////////////  Open Storage registerForActivityResult  ///////////////////

        val openStorage=registerForActivityResult(
            ActivityResultContracts.GetContent(), ActivityResultCallback {
                binding.imageViewPickImage.setImageURI(it)      // here it is URI
            }
        )
        binding.imageViewPickImage.setOnClickListener {
            showImageChooseOption(openStorage,openCamera)
        }
    }


    private fun checkForPermission(permission: ActivityResultLauncher<String>) {
        permission.launch(android.Manifest.permission.CAMERA)
    }

    private fun showImageChooseOption(
        openStorage: ActivityResultLauncher<String>,
        openCamera: ActivityResultLauncher<Intent>
    ) {
        AlertDialog.Builder(this)
            .setTitle("Choose One")
            .setPositiveButton("fromStorage", DialogInterface.OnClickListener { dialog, which ->
                openStorage.launch("image/*")
            })
            .setNegativeButton("fromCamera", DialogInterface.OnClickListener { dialog, which ->
                intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                openCamera.launch(intent)
            }).create().show()
    }
}