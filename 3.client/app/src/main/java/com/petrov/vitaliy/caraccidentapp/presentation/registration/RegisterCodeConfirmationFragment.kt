package com.petrov.vitaliy.caraccidentapp.presentation.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.petrov.vitaliy.caraccidentapp.R

class RegisterCodeConfirmationFragment: Fragment() {

    companion object {
        fun newInstance() = RegisterCodeConfirmationFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.register_code_confirmation_fragment, container, false)
    }
}