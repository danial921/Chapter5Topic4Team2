package com.example.chapter5topic4team2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.chapter5topic4team2.databinding.ActivityRegisterBinding
import com.example.chapter5topic4team2.viewmodel.AuthViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var authViewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        setContentView(binding.root)
        userRegister()
        doLogin()
    }

    private fun userRegister(){
        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val age = binding.etAge.text.toString().toInt()
            val address = binding.etAddress.text.toString().trim()
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if(name.isNotBlank() && address.isNotBlank() && username.isNotBlank() && password.isNotBlank() && age > 0){
                authViewModel.doRegister(address, age, name, username, password,"")
                authViewModel.registerObserver().observe(this){
                    if(it != null){
                        Toast.makeText(this, "Register Succesfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,LoginActivity::class.java).also{finish()})
                    }else{
                        Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "Field Cannot be empety maszzeh", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun doLogin(){
        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java).also { finish() })
        }
    }
}