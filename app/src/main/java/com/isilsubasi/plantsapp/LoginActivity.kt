package com.isilsubasi.plantsapp

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.isilsubasi.plantsapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var tf1 :Typeface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changeFontFamily()

    }


    private fun changeFontFamily(){
        tf1= Typeface.createFromAsset(getAssets(), "CinzelDecorative-Bold.ttf")
        binding.apply {
            signText.typeface=tf1
            edittextEmail.typeface=tf1
            edittextPassword.typeface=tf1
            textContinue.typeface=tf1
        }

    }

}