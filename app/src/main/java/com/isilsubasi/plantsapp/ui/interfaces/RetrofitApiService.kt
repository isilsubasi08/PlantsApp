package com.isilsubasi.plantsapp.ui.interfaces


import com.isilsubasi.plantsapp.model.UsersResponseItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitApiService {

    @GET("Users.json")
    fun fetchData(): Call<List<UsersResponseItem>>


    companion object {
        operator fun invoke(): RetrofitApiService {
            return Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/isilsubasi08/PlantsAppApi/main/data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitApiService::class.java)
        }
    }

}