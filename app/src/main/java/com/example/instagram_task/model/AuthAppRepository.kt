package com.example.instagram_task.model

import android.annotation.SuppressLint
import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class AuthAppRepository(private val application: Application) {
    val firebaseAuth: FirebaseAuth
    val userLiveData: MutableLiveData<FirebaseUser>
    val loggedOutLiveData: MutableLiveData<Boolean>

    init {
        firebaseAuth = FirebaseAuth.getInstance()
        userLiveData = MutableLiveData()
        loggedOutLiveData = MutableLiveData()
        if (firebaseAuth.currentUser != null) {
            userLiveData.postValue(firebaseAuth.currentUser)
            loggedOutLiveData.postValue(false)
        }

    }

    @SuppressLint("NewApi")
    fun login(email: String?, password: String?) {
        firebaseAuth.signInWithEmailAndPassword(email!!, password!!)
            .addOnCompleteListener(application.mainExecutor, OnCompleteListener { task ->
                if (task.isSuccessful) {
                    userLiveData.postValue(firebaseAuth.currentUser)
                    Toast.makeText(
                        application.applicationContext,
                        "Login Successful",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        application.applicationContext,
                        "Login Failure: " + task.exception!!.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }


    @SuppressLint("NewApi")
    fun register(email: String?, password: String?) {
        firebaseAuth.createUserWithEmailAndPassword(email!!, password!!)
            .addOnCompleteListener(application.mainExecutor, OnCompleteListener { task ->
                if (task.isSuccessful) {
                    firebaseAuth.uid?.let {
                        FirebaseDatabase.getInstance().getReference("users")
                            .child(it)
                            .setValue(User(email, password))
                            .addOnCompleteListener(OnCompleteListener {
                                if (task.isSuccessful) {
                                    Toast.makeText(
                                        application.applicationContext,
                                        User(email, password).toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            })
                    }
                    userLiveData.postValue(firebaseAuth.currentUser)
                    Toast.makeText(
                        application.applicationContext,
                        "Register Successfully Created",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        application.applicationContext,
                        "Registration Failure: " + task.exception!!.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }
     fun getUser(uid: String): FirebaseUser? {
        return firebaseAuth.currentUser
    }
     fun getCurrentUid(): String? {
        return firebaseAuth.currentUser?.uid
    }
    fun logOut() {
            firebaseAuth.signOut()
            loggedOutLiveData.postValue(true)
    }
}


