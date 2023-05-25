package com.isilsubasi.plantsapp.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.isilsubasi.plantsapp.databinding.ActivityHomeBinding
import com.isilsubasi.plantsapp.model.CategoryResponseItem
import com.isilsubasi.plantsapp.ui.deatil.DetailActivity
import com.isilsubasi.plantsapp.util.Constants
import com.isilsubasi.plantsapp.util.ObjectUtil


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private var categoryList:List<CategoryResponseItem>?=null
    private  var filteredList= arrayListOf<CategoryResponseItem>()
    private lateinit var categoryAdapter: HomeCategoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.e("onQueryTextSubmit",query.toString())
                return true }
            override fun onQueryTextChange(newText: String): Boolean {
                filterCategory(newText)
                return true
            }
        })
    }
    private fun initViewModel(){
        val viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.apply {
            callAPI().observe(this@HomeActivity, Observer {
                    it.run {
                        categoryList= it
                        initRecycleView(it)
                    }
            })
        }
    }

 @SuppressLint("NotifyDataSetChanged")
 fun filterCategory(newText : String){
     filteredList.clear()
     if (categoryList!=null){
         for (item in categoryList!!){
             if (item.CategoryName.toLowerCase().contains(newText.toLowerCase())){
                 filteredList.add(item) }
         }
     }else{ Log.e("plantsapp","not data") }
     filteredList.let { categoryAdapter.setData(it) }
     categoryAdapter.notifyDataSetChanged()
 }

 private fun initRecycleView(categoryList: List<CategoryResponseItem>?){
     binding.apply {
         categoryAdapter= HomeCategoryAdapter(categoryList!!,object : ItemClickListener{
             override fun onItemClick(position: Int) {
                 if (filteredList.isNotEmpty()){
                     if (filteredList.size>0){
                        val clickedCategory : CategoryResponseItem = filteredList.get(position)
                         openNextActivity(clickedCategory)
                     }else{
                         val clickedCategory : CategoryResponseItem = categoryList.get(position)
                         openNextActivity(clickedCategory) }
                 }else{
                     val clickedCategory : CategoryResponseItem = categoryList.get(position)
                     openNextActivity(clickedCategory)
                 }
             }
         })
         rcyCategory.adapter=categoryAdapter
         rcyCategory.layoutManager= StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
     }
 }
   private fun  openNextActivity(clickedCategory: CategoryResponseItem){
       val itemIntent=Intent(applicationContext,DetailActivity::class.java)
       val clickedCategoryString : String = ObjectUtil.objectToString(clickedCategory)
       itemIntent.putExtra(Constants.MOVED_DATA,clickedCategoryString)
       startActivityForResult(itemIntent, Constants.DETAIL_REQUEST_CODE)
   }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==Constants.DETAIL_REQUEST_CODE){
            binding.searchView.setQuery("", false)
            binding.searchView.clearFocus() }
    }
}