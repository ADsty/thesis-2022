package com.petrov.vitaliy.caraccidentapp.presentation.login.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.NavHostFragment
import com.petrov.vitaliy.caraccidentapp.R
import com.petrov.vitaliy.caraccidentapp.injector
import com.petrov.vitaliy.caraccidentapp.presentation.login.di.LoginModule
import com.petrov.vitaliy.caraccidentapp.presentation.login.LoginViewModel
import com.petrov.vitaliy.caraccidentapp.presentation.login.LoginViewModelFactory
import com.petrov.vitaliy.caraccidentapp.presentation.login.UserConfirmationCodeState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginRecoveryCodeConfirmationFragment : Fragment() {

    private lateinit var module: LoginModule
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        module = injector.loginModule
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.register_code_confirmation_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val phoneNumber =
            this.requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE)
                .getString("phoneNumber", "Не найдено")!!

        val factory = LoginViewModelFactory(
            module.loginUseCase,
            module.confirmationCodeGetUseCase,
            module.confirmationCodeCheckUseCase,
            module.updateUserUseCase
        )
        viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]
        viewModel.codeState.observe(viewLifecycleOwner) {
            when (it) {
                is UserConfirmationCodeState.Success -> {
                    Toast.makeText(context, it.data, Toast.LENGTH_LONG).show()
                }
                is UserConfirmationCodeState.Error -> {
                    Toast.makeText(
                        context,
                        "Не удалось подтвердить подлинность кода или код не получен",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is UserConfirmationCodeState.CodeChecked -> {
                    NavHostFragment.findNavController(this)
                        .navigate(R.id.action_loginRecoveryCodeConfirmationFragment_to_loginUpdatePasswordFragment)
                }
                else -> {

                }
            }
        }

        lifecycle.coroutineScope.launch {
            withContext(Dispatchers.IO) {
                viewModel.getConfirmationCode(phoneNumber)
            }
        }

        val button: Button = view.findViewById(R.id.button)

        val editText: EditText = view.findViewById(R.id.editText)

        button.setOnClickListener {
            if (editText.text.length == 8) {
                lifecycle.coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        viewModel.checkConfirmationCode(
                            phoneNumber,
                            editText.text.toString().toInt()
                        )
                    }
                }
            } else {
                editText.background =
                    ResourcesCompat.getDrawable(
                        resources,
                        R.drawable.edit_text_wrong_info_background,
                        resources.newTheme()
                    )
                Toast.makeText(this.requireContext(), "Введите корректный код", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        val notReceivingCodeTextView: TextView = view.findViewById(R.id.notReceivingCodeTextView)
        notReceivingCodeTextView.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_loginRecoveryCodeConfirmationFragment_to_loginPhoneConfirmationFragment)
        }
    }

}