package com.petrov.vitaliy.caraccidentapp.presentation.mainpage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.petrov.vitaliy.caraccidentapp.R

class MainPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_page_activity)
    }

    override fun onBackPressed() {
        setResult(1)
        finish()
    }

}