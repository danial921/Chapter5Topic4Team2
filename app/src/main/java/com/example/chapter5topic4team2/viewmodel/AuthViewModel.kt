package com.example.chapter5topic4team2.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.chapter5topic4team2.api.ApiService
import com.example.chapter5topic4team2.model.UsersItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel(application: Application):AndroidViewModel(application) {

    val dataLogin : MutableLiveData<MutableList<UsersItem>> = MutableLiveData()
    val dataRegister : MutableLiveData<UsersItem> = MutableLiveData()

    fun doLogin(){
        ApiService.instance.signIn().enqueue(object : Callback<MutableList<UsersItem>>{
            override fun onResponse(
                call: Call<MutableList<UsersItem>>,
                response: Response<MutableList<UsersItem>>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    dataLogin.postValue(body)
                }else{
                    Log.d("notSuccess","${response.body()}")
                }
            }

            override fun onFailure(call: Call<MutableList<UsersItem>>, t: Throwable) {
                Log.d("onFailure","${t.message}")
            }
        })
    }


    fun doRegister(address:String,age:Int,name:String,username:String,password:String,id:String){
        ApiService.instance.signUp(UsersItem(address,age,name,username,password,id))
            .enqueue(object : Callback<UsersItem>{
                override fun onResponse(call: Call<UsersItem>, response: Response<UsersItem>) {
                    if(response.isSuccessful){
                        val body = response.body()
                        dataRegister.postValue(body)
                    }else{
                        Log.d("notSuccess","${response.body()}")
                    }
                }
                override fun onFailure(call: Call<UsersItem>, t: Throwable) {
                    Log.d("OnFailure","${t.message}")
                }

            })
    }

    fun loginObserver():MutableLiveData<MutableList<UsersItem>> = dataLogin
    fun registerObserver():MutableLiveData<UsersItem> = dataRegister
}