package com.isilsubasi.plantsapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.isilsubasi.plantsapp.model.CategoryResponseItem
import com.isilsubasi.plantsapp.ui.interfaces.RetrofitApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRepository {

    fun callAPI(): MutableLiveData<List<CategoryResponseItem>> {
        val mutableLiveData = MutableLiveData<List<CategoryResponseItem>>()

        RetrofitApiService().fetchCategoryData()
            .enqueue(object : Callback<List<CategoryResponseItem>> {
                override fun onFailure(call: Call<List<CategoryResponseItem>>, t: Throwable) {
                    Log.e("isil",t.toString())
                    Log.d("Error", "Coudn't get the data")

                }
                override fun onResponse(call: Call<List<CategoryResponseItem>>, response: Response<List<CategoryResponseItem>>) {

                    Log.e("isil", response.body().toString())
                    if (response.isSuccessful) {

                        mutableLiveData.postValue(response.body())
                    } else {
                        Log.d("Error", "Coudn't get the data")
                    }

                }
            })
        return mutableLiveData
    }

}