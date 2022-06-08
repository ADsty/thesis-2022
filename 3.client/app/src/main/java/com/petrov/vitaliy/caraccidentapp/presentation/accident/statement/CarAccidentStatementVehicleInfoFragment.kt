package com.petrov.vitaliy.caraccidentapp.presentation.accident.statement

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
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.VehicleProfileUpdateRequest
import com.petrov.vitaliy.caraccidentapp.injector
import com.petrov.vitaliy.caraccidentapp.presentation.accident.statement.di.CarAccidentStatementModule
import com.petrov.vitaliy.caraccidentapp.presentation.registration.fragment.RegisterInfoRecyclerViewAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CarAccidentStatementVehicleInfoFragment : Fragment() {

    private val hints = ArrayList<String>()

    private lateinit var module: CarAccidentStatementModule
    private lateinit var viewModel: CarAccidentStatementViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        module = injector.carAccidentStatementModule
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
        var vehicleID = 0L
        hints.add("Марка и модель")
        hints.add("Идентификационный номер")
        hints.add("Госуд. рагистрационный знак")
        hints.add("Свид. о регистрации")
        hints.add("ФИО собственника машины")
        hints.add("Адрес проживания собственника")
        recyclerView.adapter = RegisterInfoRecyclerViewAdapter(hints)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val jwtToken = this.requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE)
            .getString("jwt", "null")!!
        val factory = CarAccidentStatementViewModelFactory(
            module.createCarAccidentUseCase,
            module.getUserProfileUseCase,
            module.updateUserProfileUseCase,
            module.getDriverLicenseUseCase,
            module.updateDriverLicenseUseCase,
            module.getVehicleInsurancePolicyUseCase,
            module.updateVehicleInsurancePolicyUseCase,
            module.getVehicleProfilesUseCase,
            module.updateVehicleProfileUseCase
        )
        viewModel = ViewModelProvider(this, factory)[CarAccidentStatementViewModel::class.java]

        val list = (recyclerView.adapter as RegisterInfoRecyclerViewAdapter).getHolders()

        viewModel.getVehicleProfileState.observe(viewLifecycleOwner) {
            when (it) {
                is StatementGetVehicleProfileState.Error -> {
                    Toast.makeText(
                        context,
                        "Не удалось получить данные",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is StatementGetVehicleProfileState.Success -> {
                    list[0].textInput.setText(it.data.vehicleBrand)
                    list[1].textInput.setText(it.data.vehicleVin)
                    list[2].textInput.setText(it.data.vehicleRegistrationSign)
                    list[3].textInput.setText(it.data.vehicleRegistrationCertificate)
                    list[4].textInput.setText(it.data.vehicleOwnerFullName)
                    list[5].textInput.setText(it.data.vehicleOwnerResidentialAddress)
                    vehicleID = it.data.vehicleProfileID
                    this.requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE)
                        .edit().putString("vehicleID", vehicleID.toString()).apply()
                }
                else -> {

                }
            }
        }

        viewModel.updateCreateState.observe(viewLifecycleOwner) {
            when (it) {
                is StatementUpdateCreateState.Error -> {
                    Toast.makeText(
                        context,
                        "Не удалось отправить данные, повторите еще раз",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is StatementUpdateCreateState.Update -> {
                    viewModel.changeUpdateState()
                    NavHostFragment.findNavController(this)
                        .navigate(R.id.action_carAccidentStatementVehicleInfoFragment_to_carAccidentStatementExplanationFragment)
                }
                else -> {

                }
            }
        }

        lifecycle.coroutineScope.launch {
            withContext(Dispatchers.IO) {
                viewModel.getVehicleProfile(jwtToken)
            }
        }

        val button: Button = view.findViewById(R.id.btn_submit)

        val vehicleInsurancePolicyNumber =
            this.requireActivity().getSharedPreferences("registerUser", Context.MODE_PRIVATE)
                .getString("vehicleInsurancePolicyNumber", "null")!!

        button.setOnClickListener {
            lifecycle.coroutineScope.launch {
                withContext(Dispatchers.IO) {
                    viewModel.updateVehicleProfile(
                        jwtToken, VehicleProfileUpdateRequest(
                            list[0].textInput.text.toString(),
                            list[1].textInput.text.toString(),
                            list[2].textInput.text.toString(),
                            list[3].textInput.text.toString(),
                            list[4].textInput.text.toString(),
                            list[5].textInput.text.toString(),
                            vehicleInsurancePolicyNumber
                        ), vehicleID
                    )
                }
            }
        }
    }
}