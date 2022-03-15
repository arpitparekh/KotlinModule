package com.example.kotlinmodule.text_to_speech

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import com.example.kotlinmodule.databinding.ActivityMyTextToSpeechBinding
import java.util.*

private lateinit var binding:ActivityMyTextToSpeechBinding
private lateinit var tts:TextToSpeech

class MyTextToSpeech : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMyTextToSpeechBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tts= TextToSpeech(this, TextToSpeech.OnInitListener {
            if(it==TextToSpeech.SUCCESS){
                tts.language = Locale.US
                tts.setSpeechRate(1.0F)
                tts.setPitch(1.0F)
            }
        })

        binding.btnTextToSpeech.setOnClickListener {
            val text :String=binding.edtTextToSpeech.text.toString()
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                tts.speak(text,TextToSpeech.QUEUE_FLUSH,null,null)
            }else{
                tts.speak(text,TextToSpeech.QUEUE_FLUSH,null)
            }

        }

    }
}