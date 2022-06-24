package com.petrov.vitaliy.caraccidentapp.presentation.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.petrov.vitaliy.caraccidentapp.R
import com.petrov.vitaliy.caraccidentapp.injector
import com.petrov.vitaliy.caraccidentapp.presentation.profile.di.ProfileModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileInfoFragment : Fragment() {

    private lateinit var module: ProfileModule
    private lateinit var viewModel: ProfileViewModel
    private val values = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        module = injector.profileModule
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.profile_info_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val jwtToken = this.requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE)
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

        viewModel.getUserProfileState.observe(viewLifecycleOwner) {
            when (it) {
                is ProfileGetUserProfileState.Error -> {
                    Toast.makeText(context, "Не удалось загрузить профиль", Toast.LENGTH_SHORT)
                        .show()
                }
                is ProfileGetUserProfileState.Success -> {
                    values[0] = it.data.userFullName
                    values[1] = it.data.userDateOfBirth
                    values[2] = it.data.userResidentialAddress
                    lifecycle.coroutineScope.launch {
                        withContext(Dispatchers.IO) {
                            viewModel.getVehicleProfile(jwtToken)
                        }
                    }
                }
                else -> {

                }
            }
        }

        viewModel.getVehicleProfileState.observe(viewLifecycleOwner) {
            when (it) {
                is ProfileGetVehicleProfileState.Error -> {
                    Toast.makeText(context, "Не удалось загрузить профиль", Toast.LENGTH_SHORT)
                        .show()
                }
                is ProfileGetVehicleProfileState.Success -> {
                    values[3] = it.data.vehicleBrand
                    values[4] = it.data.vehicleVin
                    values[5] = it.data.vehicleRegistrationSign
                    values[6] = it.data.vehicleRegistrationCertificate
                    values[7] = it.data.vehicleOwnerFullName
                    values[8] = it.data.vehicleOwnerResidentialAddress
                    lifecycle.coroutineScope.launch {
                        withContext(Dispatchers.IO) {
                            viewModel.getVehicleInsurancePolicy(
                                jwtToken,
                                it.data.vehicleInsurancePolicyID
                            )
                        }
                    }
                }
                else -> {

                }
            }
        }

        viewModel.getVehicleInsuranceState.observe(viewLifecycleOwner) {
            when (it) {
                is ProfileGetVehicleInsuranceState.Error -> {
                    Toast.makeText(context, "Не удалось загрузить профиль", Toast.LENGTH_SHORT)
                        .show()
                }
                is ProfileGetVehicleInsuranceState.Success -> {
                    values[9] = it.data.vehicleInsuranceCompany
                    values[10] = it.data.vehicleInsurancePolicyNumber
                    values[11] = it.data.vehicleInsurancePolicyExpirationDate
                    this.requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE)
                        .edit().putString(
                        "vehicleInsurancePolicyID",
                        it.data.vehicleInsurancePolicyID.toString()
                    ).apply()
                    lifecycle.coroutineScope.launch {
                        withContext(Dispatchers.IO) {
                            viewModel.getDriverLicense(jwtToken)
                        }
                    }
                }
                else -> {

                }
            }
        }

        viewModel.getDriverLicenseState.observe(viewLifecycleOwner) {
            when (it) {
                is ProfileGetDriverLicenseState.Error -> {
                    Toast.makeText(context, "Не удалось загрузить профиль", Toast.LENGTH_SHORT)
                        .show()
                }
                is ProfileGetDriverLicenseState.Success -> {
                    values[12] = it.data.driverLicenseNumber
                    values[13] = it.data.driverLicenseCategory
                    values[14] = it.data.driverLicenseDateOfIssue
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

        val recyclerView: RecyclerView = view.findViewById(R.id.RecyclerView)
        if (values.size == 15) {
            recyclerView.adapter = ProfileInfoRecyclerViewAdapter(values, this)
        }
        recyclerView.layoutManager = LinearLayoutManager(context)

        val bottomNavigation: BottomNavigationView =
            requireParentFragment().requireActivity().findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.messages -> NavHostFragment.findNavController(this)
                    .navigate(R.id.action_profileInfoFragment_to_chatsGeneralInfoFragment)
                R.id.user -> {}
                R.id.home -> NavHostFragment.findNavController(this)
                    .navigate(R.id.action_profileInfoFragment_to_mainPageFragment)
            }
            true
        }
    }
}