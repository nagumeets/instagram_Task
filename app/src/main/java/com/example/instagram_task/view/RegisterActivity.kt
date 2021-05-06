package com.example.instagram_task.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.instagram_task.R
import com.example.instagram_task.databinding.ActivityRegisterBinding
import com.example.instagram_task.viewModel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private var loginRegisterViewModel: RegisterViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        loginRegisterViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        loginRegisterViewModel!!.userLiveData.observe(this, { firebaseUser ->
            if (firebaseUser != null) {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
            }
        })
        binding.loginVM = loginRegisterViewModel
    }
}