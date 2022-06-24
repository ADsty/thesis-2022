package com.petrov.vitaliy.caraccidentapp.presentation.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.petrov.vitaliy.caraccidentapp.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
    }

    override fun onBackPressed() {
        setResult(1)
        finish()
    }

}