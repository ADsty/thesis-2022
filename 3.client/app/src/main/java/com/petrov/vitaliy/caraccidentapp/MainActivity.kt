package com.petrov.vitaliy.caraccidentapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.petrov.vitaliy.caraccidentapp.presentation.login.LoginActivity
import com.petrov.vitaliy.caraccidentapp.presentation.mainpage.MainPageActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        sharedPreferences = getSharedPreferences("auth", Context.MODE_PRIVATE)
    }

    override fun onResume() {
        super.onResume()
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        if (isLoggedIn) {
            val mainPageIntent = Intent(this, MainPageActivity::class.java)
            startActivityForResult(mainPageIntent, 0)
        } else {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivityForResult(loginIntent, 0)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 1)
            finish()
    }


}