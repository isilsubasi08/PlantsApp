package com.isilsubasi.plantsapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.isilsubasi.plantsapp.model.CategoryResponseItem
import com.isilsubasi.plantsapp.repository.CategoryRepository

class HomeViewModel : ViewModel() {

    fun callAPI() : MutableLiveData<List<CategoryResponseItem>>
    {
        return CategoryRepository().callAPI()
    }
}