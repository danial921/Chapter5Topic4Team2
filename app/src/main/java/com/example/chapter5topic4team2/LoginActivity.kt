package com.example.chapter5topic4team2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.chapter5topic4team2.databinding.ActivityLoginBinding
import com.example.chapter5topic4team2.model.UsersItem
import com.example.chapter5topic4team2.util.Constant
import com.example.chapter5topic4team2.viewmodel.AuthViewModel
import com.example.chapter5topic4team2.viewmodel.FilmViewModel


class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var authViewModel: AuthViewModel
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        authViewModel = ViewModelProvider(this)[AuthViewModel::class.java]
        sharedPreferences = getSharedPreferences(Constant.SHARE_PREF,Context.MODE_PRIVATE)
        setContentView(binding.root)
        userLogin()
        doRegister()
    }

    private fun userLogin(){
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            val datax : MutableList<UsersItem> = mutableListOf()

            var cekValues = false
            authViewModel.doLogin()
            authViewModel.loginObserver().observe(this){
                if(it != null){
                    it.forEach { its ->
                        datax.addAll(mutableListOf(its))
                        Log.d("YAAMPUNNN","$its")
                    }

                    val a = datax.filter {
                        it.username.equals(username) && it.password.equals(password)
                    }
                    Toast.makeText(this, "$a", Toast.LENGTH_SHORT).show()


                    if(a.isNotEmpty()){
                        var id = a[0].id
                        var name = a[0].name

                        sharedPreferences.edit().apply {
                            putString(Constant.ID,id)
                            apply()
                        }
                        startActivity(Intent(this,HomeActivity::class.java).also {
                            Toast.makeText(this, "Login Success , Hallo $name", Toast.LENGTH_SHORT).show()
                            datax.clear()
                            finish() })
                    }else{
                        Toast.makeText(this, "Username $username or password $password is wrong baby", Toast.LENGTH_SHORT).show()
                    }


                }else{
                    Toast.makeText(this, "Null", Toast.LENGTH_SHORT).show()
                }
            }




            Log.d("DATAS_LOGIN","${authViewModel.loginObserver().value}")
            Log.d("DATAS_VIEW_MODEL","${authViewModel.dataLogin.value}")
        }
    }

    private fun doRegister(){
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }

}