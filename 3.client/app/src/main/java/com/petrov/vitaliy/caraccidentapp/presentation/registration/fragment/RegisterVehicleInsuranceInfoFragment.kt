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
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.VehicleInsurancePolicyCreationRequest
import com.petrov.vitaliy.caraccidentapp.injector
import com.petrov.vitaliy.caraccidentapp.presentation.registration.CreationStateRegister
import com.petrov.vitaliy.caraccidentapp.presentation.registration.RegistrationViewModel
import com.petrov.vitaliy.caraccidentapp.presentation.registration.RegistrationViewModelFactory
import com.petrov.vitaliy.caraccidentapp.presentation.registration.di.RegistrationModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Date

class RegisterVehicleInsuranceInfoFragment : Fragment() {

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
        hints.add("Название страховой компании")
        hints.add("Номер страхового полиса")
        hints.add("Дата окончания действия")
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
                        .navigate(R.id.action_registerVehicleInsuranceInfoFragment_to_registerVehicleInfoFragment)
                }
                else -> {

                }
            }
        }

        val button: Button = view.findViewById(R.id.btn_submit)
        button.setOnClickListener {
            val list = (recyclerView.adapter as RegisterInfoRecyclerViewAdapter).getHolders()

            val vehicleInsuranceCompany = list[0].textInput.text.toString()
            val vehicleInsurancePolicyNumber = list[1].textInput.text.toString()
            val vehicleInsurancePolicyExpirationDate = list[2].textInput.text.toString()
            if (vehicleInsuranceCompany.isNotEmpty() && vehicleInsurancePolicyNumber.isNotEmpty() && vehicleInsurancePolicyExpirationDate.isNotEmpty()) {

                val jwtToken =
                    this.requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE)
                        .getString("jwt", "null")!!

                this.requireActivity().getSharedPreferences("registerUser", Context.MODE_PRIVATE)
                    .edit()
                    .putString("vehicleInsurancePolicyNumber", vehicleInsurancePolicyNumber).apply()

                lifecycle.coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        viewModel.createVehicleInsurancePolicy(
                            jwtToken,
                            VehicleInsurancePolicyCreationRequest(
                                vehicleInsuranceCompany,
                                vehicleInsurancePolicyNumber,
                                Date.valueOf(vehicleInsurancePolicyExpirationDate)
                            )
                        )
                    }
                }
            }
        }
    }
}