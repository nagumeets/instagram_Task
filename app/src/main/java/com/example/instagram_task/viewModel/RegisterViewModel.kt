package com.example.instagram_task.viewModel

import android.app.Application
import android.content.Intent
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.instagram_task.model.AuthAppRepository
import com.example.instagram_task.view.RegisterActivity
import com.example.instagram_task.view.LogInActivity
import com.google.firebase.auth.FirebaseUser

class RegisterViewModel(application: Application) :
    AndroidViewModel(application) {
    private var authAppRepository: AuthAppRepository = AuthAppRepository(application)
    var userLiveData: MutableLiveData<FirebaseUser>
    var loggedOutLiveData: MutableLiveData<Boolean>
    private val _ownerState = MutableLiveData<Boolean>()
    init {
        authAppRepository = AuthAppRepository(application)
        userLiveData = authAppRepository.userLiveData
        loggedOutLiveData = authAppRepository.loggedOutLiveData
    }

    var email = MutableLiveData<String>().apply {
        value = ""
    }
    var emailHint = MutableLiveData<String>().apply {
        value = "Enter email address"
    }
    var password = MutableLiveData<String>().apply {
        value = ""
    }
    var passHint = MutableLiveData<String>().apply {
        value = "please enter password"
    }
    var userNameHint = MutableLiveData<String>().apply {
        value = "please enter user name"
    }
    var userName = MutableLiveData<String>().apply {
        value = ""
    }
    private var toastMsg = MutableLiveData<String>()
    fun toastMessagesLiveData(): LiveData<String> = toastMsg

    fun userName(uid:String){
        authAppRepository.getUser(uid)
    }
    fun login() {
        val email = email.value.toString().trim()
        val password = password.value.toString().trim()
        if (email.length > 0 && password.length > 0) {
            authAppRepository.login(email, password)
        } else {
            toastMsg.value = "Email Address and Password Must Be Entered"
        }

    }

    fun register() {
        val email = email.value.toString().trim()
        val password = password.value.toString().trim()
        val user_Name = userName.value.toString().trim()
        if (email.length > 0 && password.length > 0&&user_Name.length>0) {
            authAppRepository.register(email, password)
        } else {
            toastMsg.value = "Email Address,Password and Username Must Be Entered"
        }
    }

    fun regNav(view: View) {
        Intent(view.context, RegisterActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun loginNav(view: View) {
        Intent(view.context, LogInActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun logOut(view: View) {
            authAppRepository.logOut()
            Intent(view.context, LogInActivity::class.java).also {
                view.context.startActivity(it)
            }
        }
}