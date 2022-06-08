package com.petrov.vitaliy.caraccidentapp.presentation.login.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.petrov.vitaliy.caraccidentapp.R

class LoginPhoneConfirmationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.register_phone_number_confirmation_fragment,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val phoneNumber =
            this.requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE)
                .getString("phoneNumber", "Не найдено")!!
        view.findViewById<EditText>(R.id.editText).setText(phoneNumber)

        val recoveryText: TextView = view.findViewById(R.id.recoveryTextView)
        recoveryText.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_loginPhoneConfirmationFragment_to_loginRecoveryFragment)
        }
        val button: Button = view.findViewById(R.id.button)
        button.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_loginPhoneConfirmationFragment_to_loginRecoveryCodeConfirmationFragment)
        }
    }

}