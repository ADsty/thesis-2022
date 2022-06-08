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
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.UserProfileUpdateRequest
import com.petrov.vitaliy.caraccidentapp.injector
import com.petrov.vitaliy.caraccidentapp.presentation.accident.statement.di.CarAccidentStatementModule
import com.petrov.vitaliy.caraccidentapp.presentation.registration.fragment.RegisterInfoRecyclerViewAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Date

class CarAccidentStatementGeneralInfoFragment : Fragment() {
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
        hints.add("Ваше ФИО")
        hints.add("Дата вашего рождения")
        hints.add("Адрес места жительства")
        hints.add("Место работы")
        hints.add("Должность на работе")
        hints.add("Рабочий номер телефона")
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

        viewModel.getUserProfileState.observe(viewLifecycleOwner) {
            when (it) {
                is StatementGetUserProfileState.Error -> {
                    Toast.makeText(
                        context,
                        "Не удалось получить данные",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is StatementGetUserProfileState.Success -> {

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
                    NavHostFragment.findNavController(this).navigate(R.id.action_carAccidentStatementGeneralInfoFragment_to_carAccidentStatementVehicleInsuranceFragment)
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

        val button: Button = view.findViewById(R.id.btn_submit)

        val driverLicense = this.requireActivity().getSharedPreferences("registerUser", Context.MODE_PRIVATE)
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