package com.isilsubasi.plantsapp.ui.deatil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.isilsubasi.plantsapp.R
import com.isilsubasi.plantsapp.databinding.ActivityDetailBinding
import com.isilsubasi.plantsapp.databinding.ActivityMainBinding
import com.isilsubasi.plantsapp.model.CategoryResponseItem
import com.isilsubasi.plantsapp.util.Constants
import com.isilsubasi.plantsapp.util.ObjectUtil
import com.isilsubasi.plantsapp.util.loadImage

class DetailActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    private fun init(){
        val moveItemString=intent.getStringExtra(Constants.MOVED_DATA)
        val itemModel : CategoryResponseItem = ObjectUtil.jsonStringToObject(moveItemString.toString())
        binding.apply {
            textView.text=itemModel.CategoryName
            detayImageView.loadImage(itemModel.CategoryImage)
        }
    }
}