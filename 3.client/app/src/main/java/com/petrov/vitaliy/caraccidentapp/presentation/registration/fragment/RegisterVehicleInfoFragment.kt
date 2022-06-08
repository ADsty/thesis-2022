package com.petrov.vitaliy.caraccidentapp.presentation.registration.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.petrov.vitaliy.caraccidentapp.R
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.VehicleProfileCreationRequest
import com.petrov.vitaliy.caraccidentapp.injector
import com.petrov.vitaliy.caraccidentapp.presentation.mainpage.MainPageActivity
import com.petrov.vitaliy.caraccidentapp.presentation.registration.CreationStateRegister
import com.petrov.vitaliy.caraccidentapp.presentation.registration.RegistrationViewModel
import com.petrov.vitaliy.caraccidentapp.presentation.registration.RegistrationViewModelFactory
import com.petrov.vitaliy.caraccidentapp.presentation.registration.di.RegistrationModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterVehicleInfoFragment : Fragment() {

    private val hints = ArrayList<String>()

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
        return inflater.inflate(R.layout.register_info_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.rv_fields)
        hints.add("Марка и модель")
        hints.add("Идентификационный номер")
        hints.add("Госуд. рагистрационный знак")
        hints.add("Свид. о регистрации")
        hints.add("ФИО собственника машины")
        hints.add("Адрес проживания собственника")
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
                    val intent = Intent(context, MainPageActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                else -> {

                }
            }
        }

        val button: Button = view.findViewById(R.id.btn_submit)
        button.setOnClickListener {
            val list = (recyclerView.adapter as RegisterInfoRecyclerViewAdapter).getHolders()

            val vehicleBrand = list[0].textInput.text.toString()
            val vehicleVIN = list[1].textInput.text.toString()
            val vehicleRegistrationSign = list[2].textInput.text.toString()
            val vehicleRegistrationCertificate = list[3].textInput.text.toString()
            val vehicleOwnerFullName = list[4].textInput.text.toString()
            val vehicleOwnerResidentialAddress = list[5].textInput.text.toString()
            val vehicleInsurancePolicyNumber =
                this.requireActivity().getSharedPreferences("registerUser", Context.MODE_PRIVATE)
                    .getString("vehicleInsurancePolicyNumber", "null")!!

            if (vehicleBrand.isNotEmpty() && vehicleVIN.isNotEmpty() && vehicleRegistrationSign.isNotEmpty() && vehicleRegistrationCertificate.isNotEmpty() && vehicleOwnerFullName.isNotEmpty() && vehicleOwnerResidentialAddress.isNotEmpty() && vehicleInsurancePolicyNumber.isNotEmpty()) {

                val jwtToken =
                    this.requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE)
                        .getString("jwt", "null")!!

                lifecycle.coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        viewModel.createVehicleProfile(
                            jwtToken,
                            VehicleProfileCreationRequest(
                                vehicleBrand,
                                vehicleVIN,
                                vehicleRegistrationSign,
                                vehicleRegistrationCertificate,
                                vehicleOwnerFullName,
                                vehicleOwnerResidentialAddress,
                                vehicleInsurancePolicyNumber
                            )
                        )
                    }
                }
            }
        }
    }
}