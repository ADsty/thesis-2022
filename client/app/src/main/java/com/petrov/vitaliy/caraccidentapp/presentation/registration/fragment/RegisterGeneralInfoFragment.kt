package com.petrov.vitaliy.caraccidentapp.presentation.registration.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.petrov.vitaliy.caraccidentapp.R
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.UserProfileCreationRequest
import com.petrov.vitaliy.caraccidentapp.injector
import com.petrov.vitaliy.caraccidentapp.presentation.registration.CreationStateRegister
import com.petrov.vitaliy.caraccidentapp.presentation.registration.RegistrationViewModel
import com.petrov.vitaliy.caraccidentapp.presentation.registration.RegistrationViewModelFactory
import com.petrov.vitaliy.caraccidentapp.presentation.registration.di.RegistrationModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Date

class RegisterGeneralInfoFragment : Fragment() {

    private lateinit var module: RegistrationModule
    private lateinit var viewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        module = injector.registrationModule
    }

    private val hints = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.register_info_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.rv_fields)
        hints.add("Ваше имя")
        hints.add("Ваша фамилия")
        hints.add("Ваше отчество (если имеется)")
        hints.add("Дата вашего рождения")
        hints.add("Адрес места жительства")
        hints.add("Место работы")
        hints.add("Должность на работе")
        hints.add("Рабочий номер телефона")
        recyclerView.adapter = RegisterInfoRecyclerViewAdapter(hints)
        recyclerView.layoutManager = LinearLayoutManager(context)

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
        viewModel.creationState.observe(viewLifecycleOwner) {
            when (it) {
                is CreationStateRegister.Error -> {
                    Toast.makeText(
                        context,
                        "Не удалось отправить данные, повторите еще раз",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is CreationStateRegister.Success -> {
                    NavHostFragment.findNavController(this)
                        .navigate(R.id.action_registerGeneralInfoFragment_to_registerVehicleInfoChoiceFragment)
                }
                else -> {

                }
            }
        }

        val button: Button = view.findViewById(R.id.btn_submit)

        button.setOnClickListener {
            val list = (recyclerView.adapter as RegisterInfoRecyclerViewAdapter).getHolders()

            val fullName =
                list[0].textInput.text.toString() + list[1].textInput.text.toString() + list[2].textInput.text.toString()
            val dateOfBirth = list[3].textInput.text.toString()
            val residentialAddress = list[4].textInput.text.toString()
            val placeOfWork = list[5].textInput.text.toString()
            val positionAtWork = list[6].textInput.text.toString()
            val workPhoneNumber = list[7].textInput.text.toString()
            val driverLicenseNumber =
                this.requireActivity().getSharedPreferences("registerUser", Context.MODE_PRIVATE)
                    .getString("driverLicenseNumber", "")!!

            if (driverLicenseNumber.isNotEmpty() && fullName.isNotEmpty() && dateOfBirth.isNotEmpty() && residentialAddress.isNotEmpty() && placeOfWork.isNotEmpty() && positionAtWork.isNotEmpty() && workPhoneNumber.isNotEmpty()) {

                val jwtToken =
                    this.requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE)
                        .getString("jwt", "null")!!

                lifecycle.coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        viewModel.createUserProfile(
                            jwtToken,
                            UserProfileCreationRequest(
                                fullName,
                                Date.valueOf(dateOfBirth),
                                residentialAddress,
                                placeOfWork,
                                positionAtWork,
                                workPhoneNumber,
                                driverLicenseNumber
                            )
                        )
                    }
                }
            }
        }
    }
}