package com.example.kotlinmodule.speech_to_text

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.example.kotlinmodule.databinding.ActivityMySpeechToTextBinding
import java.util.*

private lateinit var binding:ActivityMySpeechToTextBinding

class MySpeechToTextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMySpeechToTextBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val speechToText=registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback {
                if(it.resultCode== RESULT_OK && it.data!=null){
                    val result= it.data!!.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS)
                    binding.tvTextToSpeech.text = Objects.requireNonNull(result)?.get(0)

                }
            })

        binding.imageViewSpeechToText.setOnClickListener {
            val intent= Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Speak To Text")

            try{
                speechToText.launch(intent)
            }catch (exception : ActivityNotFoundException){
                val appPackageName :String ="com.google.android.googlequicksearchbox"
                speechToText.launch(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
            }
        }
    }
}