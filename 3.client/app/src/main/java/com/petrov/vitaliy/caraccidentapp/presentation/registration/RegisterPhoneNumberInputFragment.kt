package com.petrov.vitaliy.caraccidentapp.presentation.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.petrov.vitaliy.caraccidentapp.R

class RegisterPhoneNumberInputFragment: Fragment() {

    companion object {
        fun newInstance() = RegisterPhoneNumberInputFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.register_phone_number_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val codeButton: Button = view.findViewById(R.id.button)
        codeButton.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_registerPhoneNumberInputFragment_to_registerCodeConfirmationFragment)
        }

    }
}