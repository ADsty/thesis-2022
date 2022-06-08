package com.petrov.vitaliy.caraccidentapp.presentation.login.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.NavHostFragment
import com.petrov.vitaliy.caraccidentapp.R
import com.petrov.vitaliy.caraccidentapp.injector
import com.petrov.vitaliy.caraccidentapp.presentation.login.di.LoginModule
import com.petrov.vitaliy.caraccidentapp.presentation.login.LoginViewModel
import com.petrov.vitaliy.caraccidentapp.presentation.login.LoginViewModelFactory
import com.petrov.vitaliy.caraccidentapp.presentation.login.UserLoginState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WrongLoginFragment : Fragment() {

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
        return inflater.inflate(R.layout.wrong_login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val registerTextView: TextView = view.findViewById(R.id.registerTextView)
        val phoneForm: EditText = view.findViewById(R.id.phoneForm)
        val password: EditText = view.findViewById(R.id.passwordForm)
        val factory = LoginViewModelFactory(
            module.loginUseCase,
            module.confirmationCodeGetUseCase,
            module.confirmationCodeCheckUseCase,
            module.updateUserUseCase
        )
        viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        viewModel.eventsState.observe(viewLifecycleOwner) {
            when (it) {
                is UserLoginState.Success -> {
                    NavHostFragment.findNavController(this)
                        .navigate(R.id.action_loginFragment_to_mainPageActivity)
                }
                is UserLoginState.Error -> {
                    Toast.makeText(context, "Не удалось зайти в аккаунт", Toast.LENGTH_LONG).show()
                }
                else -> {

                }
            }
        }

        registerTextView.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_loginFragment_to_registerPhoneNumberInputFragment)
        }

        val loginButton: Button = view.findViewById(R.id.loginButton)
        loginButton.setOnClickListener {
            lifecycle.coroutineScope.launch {
                withContext(Dispatchers.IO) {
                    viewModel.login(phoneForm.text.toString(), password.text.toString())
                }
            }
        }

        val loginRecoveryTextView: TextView = view.findViewById(R.id.loginRecoveryTextView)
        loginRecoveryTextView.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_wrongLoginFragment_to_loginRecoveryFragment)
        }

    }

}