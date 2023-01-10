package com.isilsubasi.plantsapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.isilsubasi.plantsapp.model.UsersResponseItem
import com.isilsubasi.plantsapp.ui.interfaces.RetrofitApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    fun callAPI(): MutableLiveData<List<UsersResponseItem>> {
        val mutableLiveData = MutableLiveData<List<UsersResponseItem>>()

        RetrofitApiService().fetchData()
            .enqueue(object : Callback<List<UsersResponseItem>>{
                override fun onFailure(call: Call<List<UsersResponseItem>>, t: Throwable) {
                    Log.e("isil",t.toString())
                    Log.d("Error", "Coudn't get the data")

                }
                override fun onResponse(call: Call<List<UsersResponseItem>>, response: Response<List<UsersResponseItem>>) {

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