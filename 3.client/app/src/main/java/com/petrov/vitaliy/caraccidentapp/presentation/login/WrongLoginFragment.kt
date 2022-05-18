package com.petrov.vitaliy.caraccidentapp.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.petrov.vitaliy.caraccidentapp.R

class WrongLoginFragment: Fragment() {

    companion object {
        fun newInstance() = WrongLoginFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.wrong_login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val registerTextView: TextView = view.findViewById(R.id.loginRecoveryTextView)
        registerTextView.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_wrongLoginFragment_to_loginRecoveryFragment)
        }
    }

}