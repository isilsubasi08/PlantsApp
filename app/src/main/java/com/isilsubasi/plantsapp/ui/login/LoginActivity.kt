package com.isilsubasi.plantsapp.ui.login

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.isilsubasi.plantsapp.databinding.ActivityLoginBinding
import com.isilsubasi.plantsapp.model.UsersResponseItem
import com.isilsubasi.plantsapp.ui.home.HomeActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var userList:List<UsersResponseItem>?=null
    private lateinit var tf1 :Typeface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        init()

    }


    private fun init(){
        changeFontText()
        binding.apply {
            buttonSingIn.setOnClickListener {
                nullCheck()
            }
        }

    }

    private fun changeFontText(){
        tf1= Typeface.createFromAsset(getAssets(), "CinzelDecorative-Bold.ttf")
        binding.apply {
            signText.typeface=tf1
            textContinue.typeface=tf1
        }
    }

    private fun nullCheck(){
            var email : String?=binding.edittextEmail.text.toString().trim()
            var password : String?=binding.edittextPassword.text.toString().trim()
            if (email.equals("") || password.equals("")){
                Toast.makeText(applicationContext,"Lütfen boş alanları kontrol ediniz.",Toast.LENGTH_LONG).show()
            }else{
                if (emailFormatValid(email) == true){
                 //initviewmodel
                    initViewModel(email,password)



                }else{
                    //formata uygun değil
                    Toast.makeText(applicationContext,"Email formata uygun değil",Toast.LENGTH_LONG).show()
                }
            }


    }

    private fun emailFormatValid(email: CharSequence?) : Boolean{
        if (TextUtils.isEmpty(email)) {
            return true
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }

    private fun initViewModel(email : String?,password : String?){
        var viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        viewModel.apply {
            callAPI().observe(this@LoginActivity, Observer {
                it.run {
                    checkUser(it,email,password)
                }
            })
        }

    }

    private fun checkUser(it: List<UsersResponseItem>, email: String?, password: String?){
        userList= it
        var userQty:Int=1
        for (userViewModel : UsersResponseItem in userList!!){
                if(userViewModel.Email.equals(email) && userViewModel.Parola.equals(password)){
                    var userName : String = userViewModel.Adi
                    var userSurname : String = userViewModel.Soyadi
                    //diğer aktiviteye gönderilecek veriler
                    Toast.makeText(applicationContext,"Giriş başarılı",Toast.LENGTH_LONG).show()
                    openNextActivity()
                }
                else{
                    userQty++
                    if (userQty==userList!!.size){
                        Toast.makeText(applicationContext,"Giriş başarısız",Toast.LENGTH_LONG).show()
                        break
                    }
                }
        }

    }

    private fun openNextActivity(){
        val intent = Intent(applicationContext,HomeActivity::class.java)
        startActivity(intent)
    }


}