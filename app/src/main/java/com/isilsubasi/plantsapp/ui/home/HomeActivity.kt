package com.isilsubasi.plantsapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.isilsubasi.plantsapp.databinding.ActivityHomeBinding
import com.isilsubasi.plantsapp.model.CategoryResponseItem


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    var categoryList= listOf<CategoryResponseItem>()
    var categoryAdapter: HomeCategoryAdapter =HomeCategoryAdapter(categoryList)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.e("onQueryTextSubmit",query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.e("onQueryTextChange",newText.toString())
                filterList(newText)
                return true


            }


        })

    }

    private fun initViewModel(){


        binding.rcyCategory.setHasFixedSize(true)
        binding.rcyCategory.layoutManager = GridLayoutManager(this,3)
        var viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.apply {
            callAPI().observe(this@HomeActivity, Observer {
                    categoryAdapter=HomeCategoryAdapter(it)
                    categoryList= it
                    binding.rcyCategory.adapter=categoryAdapter
                    categoryAdapter.notifyDataSetChanged()


            }

            )
        }
    }




    private fun filterList(text: String?){
        val filteredList :ArrayList<CategoryResponseItem> = ArrayList()

        if (text !=null){
            for (item in categoryList){
                if (item.CategoryName.toLowerCase().contains(text.toLowerCase())){

                    filteredList.add(item)

                }


            }
            if (filteredList.isEmpty()){
                Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
            }else
            {
                categoryAdapter.filterList(filteredList)
            }

        }





    }




}