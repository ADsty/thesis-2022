package com.petrov.vitaliy.caraccidentapp.presentation.login.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.petrov.vitaliy.caraccidentapp.R

class LoginRecoveryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.login_recovery_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button: Button = view.findViewById(R.id.button)
        val editTextPhone: EditText = view.findViewById(R.id.editTextPhone)
        button.setOnClickListener {
            if (editTextPhone.text.toString().length == 10) {
                this.requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE).edit()
                    .putString("phoneNumber", "8" + editTextPhone.text.toString()).apply()
                NavHostFragment.findNavController(this)
                    .navigate(R.id.action_loginRecoveryFragment_to_loginRecoveryCodeConfirmationFragment)
            } else {
                editTextPhone.background = ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.edit_text_wrong_info_background,
                    resources.newTheme()
                )
                Toast.makeText(
                    this.requireContext(),
                    "Введите корректный номер телефона без +7",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}