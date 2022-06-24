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
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.UserProfileUpdateRequest
import com.petrov.vitaliy.caraccidentapp.injector
import com.petrov.vitaliy.caraccidentapp.presentation.profile.di.ProfileModule
import com.petrov.vitaliy.caraccidentapp.presentation.registration.fragment.RegisterInfoRecyclerViewAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Date

class ProfileGeneralInfoChangeActivity : AppCompatActivity() {

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
        hints.add("Ваше ФИО")
        hints.add("Дата вашего рождения")
        hints.add("Адрес места жительства")
        hints.add("Место работы")
        hints.add("Должность на работе")
        hints.add("Рабочий номер телефона")
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

        viewModel.getUserProfileState.observe(this) {
            when (it) {
                is ProfileGetUserProfileState.Error -> {
                    Toast.makeText(
                        this,
                        "Не удалось получить данные",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is ProfileGetUserProfileState.Success -> {
                    list[0].textInput.setText(it.data.userFullName)
                    list[1].textInput.setText(it.data.userDateOfBirth)
                    list[2].textInput.setText(it.data.userResidentialAddress)
                    list[3].textInput.setText(it.data.userPlaceOfWork)
                    list[4].textInput.setText(it.data.userPositionAtWork)
                    list[5].textInput.setText(it.data.userWorkPhoneNumber)
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
                viewModel.getUserProfile(jwtToken)
            }
        }

        val button: Button = findViewById(R.id.btn_submit)

        val driverLicense = this.getSharedPreferences("registerUser", Context.MODE_PRIVATE)
            .getString("driverLicenseNumber", "")!!

        button.setOnClickListener {
            lifecycle.coroutineScope.launch {
                withContext(Dispatchers.IO) {
                    viewModel.updateUserProfile(
                        jwtToken, UserProfileUpdateRequest(
                            list[0].textInput.text.toString(),
                            Date.valueOf(list[1].textInput.text.toString()),
                            list[2].textInput.text.toString(),
                            list[3].textInput.text.toString(),
                            list[4].textInput.text.toString(),
                            list[5].textInput.text.toString(),
                            driverLicense
                        )
                    )
                }
            }
        }
    }
}