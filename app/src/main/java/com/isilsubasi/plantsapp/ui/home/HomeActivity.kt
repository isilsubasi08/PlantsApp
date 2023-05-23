package com.isilsubasi.plantsapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.isilsubasi.plantsapp.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initViewModel()


    }

    private fun initViewModel(){

        binding.rcyCategory.setHasFixedSize(true)
        binding.rcyCategory.layoutManager = GridLayoutManager(this,3)
        var viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.apply {
            callAPI().observe(this@HomeActivity, Observer {
                    binding.rcyCategory.adapter=HomeCategoryAdapter(it)
                    binding.userText.text=intent.getStringExtra("userName")



            }

            )
        }
    }

}