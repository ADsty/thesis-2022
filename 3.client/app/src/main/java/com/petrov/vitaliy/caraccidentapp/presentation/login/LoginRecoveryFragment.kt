package com.petrov.vitaliy.caraccidentapp.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.petrov.vitaliy.caraccidentapp.R

class LoginRecoveryFragment: Fragment() {

    companion object {
        fun newInstance() = LoginRecoveryFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.login_recovery_fragment, container, false)
    }

}