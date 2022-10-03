package com.example.chapter5topic4team2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.chapter5topic4team2.databinding.ActivityLoginBinding
import com.example.chapter5topic4team2.model.UsersItem
import com.example.chapter5topic4team2.viewmodel.AuthViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var authViewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        setContentView(binding.root)
        userLogin()
        doRegister()
    }

    private fun userLogin(){
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etUsername.text.toString().trim()
            val datas : MutableList<UsersItem> = mutableListOf()
            authViewModel.doLogin()
            authViewModel.loginObserver().observe(this){
                if(it != null){
                    it.forEach { its ->
                        datas.addAll(mutableListOf(its))
                    }
                    Toast.makeText(this, "$datas", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show()
                }
            }
            Log.d("DATAS_LOGIN","${authViewModel.loginObserver().value}")
            Log.d("DATAS_VIEW_MODEL","${authViewModel.dataLogin.value}")
            Toast.makeText(this, "$datas", Toast.LENGTH_SHORT).show()

        }
    }

    private fun doRegister(){
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }
}