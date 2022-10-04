package com.example.chapter5topic4team2.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chapter5topic4team2.api.ApiService
import com.example.chapter5topic4team2.model.UsersItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel: ViewModel() {

    val dataLogin : MutableLiveData<ArrayList<UsersItem>> = MutableLiveData()
    val dataRegister : MutableLiveData<UsersItem> = MutableLiveData()

    fun doLogin(){
        ApiService.instance.signIn().enqueue(object : Callback<ArrayList<UsersItem>>{
            override fun onResponse(
                call: Call<ArrayList<UsersItem>>,
                response: Response<ArrayList<UsersItem>>
            ) {
                if(response.isSuccessful){
                    val body = response.body()
                    dataLogin.postValue(body)
                }else{
                    Log.d("notSuccess","${response.body()}")
                    dataLogin.postValue(null)
                }
            }

            override fun onFailure(call: Call<ArrayList<UsersItem>>, t: Throwable) {
                dataLogin.postValue(null)
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
                        dataRegister.postValue(null)
                        Log.d("notSuccess","${response.body()}")
                    }
                }
                override fun onFailure(call: Call<UsersItem>, t: Throwable) {
                    dataRegister.postValue(null)
                    Log.d("OnFailure","${t.message}")
                }

            })
    }

    fun loginObserver():MutableLiveData<ArrayList<UsersItem>> = dataLogin
    fun registerObserver():MutableLiveData<UsersItem> = dataRegister
}