package com.petrov.vitaliy.caraccidentapp.presentation.registration.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.NavHostFragment
import com.petrov.vitaliy.caraccidentapp.R
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.UserRegistrationRequest
import com.petrov.vitaliy.caraccidentapp.injector
import com.petrov.vitaliy.caraccidentapp.presentation.registration.RegisterUserState
import com.petrov.vitaliy.caraccidentapp.presentation.registration.RegistrationViewModel
import com.petrov.vitaliy.caraccidentapp.presentation.registration.RegistrationViewModelFactory
import com.petrov.vitaliy.caraccidentapp.presentation.registration.di.RegistrationModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterPasswordFragment : Fragment() {

    private lateinit var module: RegistrationModule
    private lateinit var viewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        module = injector.registrationModule
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.new_password_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val phoneNumber =
            this.requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE)
                .getString("phoneNumber", "Не найдено")!!
        val factory = RegistrationViewModelFactory(
            module.confirmationCodeGetUseCase,
            module.confirmationCodeCheckUseCase,
            module.registerNewUserUseCase,
            module.createDriverLicenseUseCase,
            module.createUserProfileUseCase,
            module.createVehicleInsurancePolicyUseCase,
            module.createVehicleProfileUseCase
        )
        viewModel = ViewModelProvider(this, factory)[RegistrationViewModel::class.java]
        viewModel.registerState.observe(viewLifecycleOwner) {
            when (it) {
                is RegisterUserState.Success -> {
                    this.requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE)
                        .edit().putString("jwt", it.jwt).apply()
                    NavHostFragment.findNavController(this)
                        .navigate(R.id.action_registerPasswordFragment_to_registerDriverLicenseFragment)
                }
                is RegisterUserState.Error -> {
                    Toast.makeText(
                        context,
                        "Не удалось зарегистрировать пользователя",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {

                }
            }
        }

        val button: Button = view.findViewById(R.id.button)
        val editText: EditText = view.findViewById(R.id.editTextPassword)

        button.setOnClickListener {
            lifecycle.coroutineScope.launch {
                withContext(Dispatchers.IO) {
                    viewModel.registerNewUser(
                        UserRegistrationRequest(
                            phoneNumber,
                            editText.text.toString()
                        )
                    )
                }
            }
        }
    }
}