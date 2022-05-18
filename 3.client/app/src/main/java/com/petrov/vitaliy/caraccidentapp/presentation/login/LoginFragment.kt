package com.petrov.vitaliy.caraccidentapp.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import com.petrov.vitaliy.caraccidentapp.R
import com.petrov.vitaliy.caraccidentapp.presentation.mainpage.MainPageActivity

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val registerTextView: TextView = view.findViewById(R.id.registerTextView)
        registerTextView.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_wrongLoginFragment)
        }
        val loginButton: Button = view.findViewById(R.id.loginButton)
        loginButton.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_mainPageActivity)
        }

    }
}