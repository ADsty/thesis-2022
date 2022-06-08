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
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.VehicleInsurancePolicyUpdateRequest
import com.petrov.vitaliy.caraccidentapp.injector
import com.petrov.vitaliy.caraccidentapp.presentation.accident.statement.di.CarAccidentStatementModule
import com.petrov.vitaliy.caraccidentapp.presentation.registration.fragment.RegisterInfoRecyclerViewAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Date

class CarAccidentStatementVehicleInsuranceFragment: Fragment() {
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
        hints.add("Название страховой компании")
        hints.add("Номер страхового полиса")
        hints.add("Дата окончания действия")
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

        viewModel.getVehicleInsuranceState.observe(viewLifecycleOwner) {
            when (it) {
                is StatementGetVehicleInsuranceState.Error -> {
                    Toast.makeText(
                        context,
                        "Не удалось получить данные",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is StatementGetVehicleInsuranceState.Success -> {
                    list[0].textInput.setText(it.data.vehicleInsuranceCompany)
                    list[1].textInput.setText(it.data.vehicleInsurancePolicyNumber)
                    list[2].textInput.setText(it.data.vehicleInsurancePolicyExpirationDate)
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
                    NavHostFragment.findNavController(this).navigate(R.id.action_carAccidentStatementVehicleInsuranceFragment_to_carAccidentStatementVehicleInfoFragment)
                }
                else -> {

                }
            }
        }

        val vehicleInsuranceID = this.requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE)
            .getString("vehicleInsurancePolicyID", "")!!

        lifecycle.coroutineScope.launch {
            withContext(Dispatchers.IO) {
                viewModel.getVehicleInsurancePolicy(jwtToken, vehicleInsuranceID.toLong())
            }
        }

        val button: Button = view.findViewById(R.id.btn_submit)

        button.setOnClickListener {
            lifecycle.coroutineScope.launch {
                withContext(Dispatchers.IO) {
                    viewModel.updateVehicleInsurancePolicy(
                        jwtToken, VehicleInsurancePolicyUpdateRequest(
                            list[0].textInput.text.toString(),
                            list[1].textInput.text.toString(),
                            Date.valueOf(list[2].textInput.text.toString())
                        )
                    )
                }
            }
        }
    }
}