package com.isilsubasi.plantsapp.ui

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.isilsubasi.plantsapp.databinding.ActivityMainBinding
import com.isilsubasi.plantsapp.ui.login.LoginActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tf1 :Typeface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        hideStatusBar()
        changeFontFamily()
        setOnClickListener()

    }


    fun hideStatusBar(){
        //hide status bar
        // getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val decorView: View = window.decorView
        val uiOptions: Int = View.SYSTEM_UI_FLAG_FULLSCREEN
        decorView.setSystemUiVisibility(uiOptions)

    }

    fun changeFontFamily(){
        tf1= Typeface.createFromAsset(getAssets(), "Monoton-Regular.ttf")
        binding.apply {
            welcomeText.typeface=tf1
            letsButton.typeface=tf1
        }

    }

    fun setOnClickListener(){
        binding.letsButton.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}