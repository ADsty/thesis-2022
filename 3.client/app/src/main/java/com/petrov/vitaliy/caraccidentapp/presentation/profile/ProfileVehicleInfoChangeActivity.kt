package com.petrov.vitaliy.caraccidentapp.presentation.profile

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.petrov.vitaliy.caraccidentapp.R
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.VehicleProfileUpdateRequest
import com.petrov.vitaliy.caraccidentapp.injector
import com.petrov.vitaliy.caraccidentapp.presentation.profile.di.ProfileModule
import com.petrov.vitaliy.caraccidentapp.presentation.registration.fragment.RegisterInfoRecyclerViewAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileVehicleInfoChangeActivity : AppCompatActivity() {

    private val hints = ArrayList<String>()

    private lateinit var module: ProfileModule
    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        module = injector.profileModule
        setContentView(R.layout.register_info_fragment)
    }

    override fun onResume() {
        super.onResume()
        val recyclerView: RecyclerView = findViewById(R.id.rv_fields)
        var vehicleID = 0L
        hints.add("Марка и модель")
        hints.add("Идентификационный номер")
        hints.add("Госуд. рагистрационный знак")
        hints.add("Свид. о регистрации")
        hints.add("ФИО собственника машины")
        hints.add("Адрес проживания собственника")
        recyclerView.adapter = RegisterInfoRecyclerViewAdapter(hints)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val jwtToken = this.getSharedPreferences("userData", Context.MODE_PRIVATE)
            .getString("jwt", "null")!!
        val factory = ProfileViewModelFactory(
            module.getUserProfileUseCase,
            module.updateUserProfileUseCase,
            module.getDriverLicenseUseCase,
            module.updateDriverLicenseUseCase,
            module.getVehicleInsurancePolicyUseCase,
            module.updateVehicleInsurancePolicyUseCase,
            module.getVehicleProfilesUseCase,
            module.updateVehicleProfileUseCase
        )
        viewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]

        val list = (recyclerView.adapter as RegisterInfoRecyclerViewAdapter).getHolders()

        viewModel.getVehicleProfileState.observe(this) {
            when (it) {
                is ProfileGetVehicleProfileState.Error -> {
                    Toast.makeText(
                        this,
                        "Не удалось получить данные",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is ProfileGetVehicleProfileState.Success -> {
                    list[0].textInput.setText(it.data.vehicleBrand)
                    list[1].textInput.setText(it.data.vehicleVin)
                    list[2].textInput.setText(it.data.vehicleRegistrationSign)
                    list[3].textInput.setText(it.data.vehicleRegistrationCertificate)
                    list[4].textInput.setText(it.data.vehicleOwnerFullName)
                    list[5].textInput.setText(it.data.vehicleOwnerResidentialAddress)
                    vehicleID = it.data.vehicleProfileID
                }
                else -> {

                }
            }
        }

        viewModel.updateState.observe(this) {
            when (it) {
                is ProfileUpdateState.Error -> {
                    Toast.makeText(
                        this,
                        "Не удалось отправить данные, повторите еще раз",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is ProfileUpdateState.Success -> {
                    viewModel.changeUpdateState()
                    finish()
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

        val button: Button = findViewById(R.id.btn_submit)

        val vehicleInsurancePolicyNumber =
            this.getSharedPreferences("registerUser", Context.MODE_PRIVATE)
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