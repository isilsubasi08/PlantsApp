package com.isilsubasi.plantsapp.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.isilsubasi.plantsapp.model.UsersResponseItem
import com.isilsubasi.plantsapp.repository.UserRepository

class UserViewModel : ViewModel(){


    fun callAPI() : MutableLiveData<List<UsersResponseItem>>
    {
        //repository sınıfının callapı metodu çağrılır.
        return UserRepository().callAPI()
    }

}