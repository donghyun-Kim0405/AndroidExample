package com.example.testtospeechexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var btn : Button
    lateinit var editText : EditText
    var tts : TextToSpeech? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //tts
        tts = TextToSpeech(this, TextToSpeech.OnInitListener {
            if(it == android.speech.tts.TextToSpeech.ERROR){
                tts?.setLanguage(Locale.ENGLISH)    //tts 언어 설정
            }
            tts?.setPitch(1.0f)     //높이설정
            tts?.setSpeechRate(1.0f)    //읽기 속도 설정
        })
        //tts

        editText = findViewById(R.id.inputArea)
        btn = findViewById(R.id.btn_speech)
        btn.setOnClickListener {
            tts?.speak(editText.text.toString(), TextToSpeech.QUEUE_FLUSH, null)    //text to speech 수행
        }
    }

    override fun onDestroy() {
        if(tts !=null){
            tts!!.stop()
            tts!!.shutdown()
            tts = null
        }

        super.onDestroy()
    }

}