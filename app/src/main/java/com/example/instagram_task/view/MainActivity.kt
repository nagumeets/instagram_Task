package com.example.instagram_task.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.instagram_task.R
import kotlinx.android.synthetic.main.layout_app_bar.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }
}