package com.petrov.vitaliy.caraccidentapp.presentation.accident.statement

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
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.accident.CarAccidentCreationRequest
import com.petrov.vitaliy.caraccidentapp.injector
import com.petrov.vitaliy.caraccidentapp.presentation.accident.statement.di.CarAccidentStatementModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Date
import java.sql.Time
import java.time.LocalDate
import java.time.LocalTime

class CarAccidentStatementSynchronizationFragment : Fragment() {

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
        return inflater.inflate(R.layout.share_code_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userID = this.requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE)
            .getString("userID", "0")!!
        val vehicleID =
            this.requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE)
                .getString("vehicleID", "0")!!
        val clientCode: EditText = view.findViewById(R.id.et_client_code)
        clientCode.setText("$userID-$vehicleID")
        val btnConnectUser: Button = view.findViewById(R.id.btn_connect_client)
        btnConnectUser.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_carAccidentStatementSynchronizationFragment_to_carAccidentStatementUserAdditionFragment)
        }
        val btnSend: Button = view.findViewById(R.id.btn_send)

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

        viewModel.updateCreateState.observe(viewLifecycleOwner) {
            when (it) {
                is StatementUpdateCreateState.Error -> {
                    Toast.makeText(context, "Не удалось сообщить о ДТП", Toast.LENGTH_SHORT).show()
                }
                is StatementUpdateCreateState.Create -> {
                    NavHostFragment.findNavController(this)
                        .navigate(R.id.action_carAccidentStatementSynchronizationFragment_to_carAccidentStatementFinalizationFragment)
                }
                else -> {

                }
            }
        }

        btnSend.setOnClickListener {
            val syncSet = this.requireActivity()
                .getSharedPreferences("synchronizeUsers", Context.MODE_PRIVATE)
                .getStringSet("sync", setOf())!!
            val userIDs = ArrayList<Long>()
            val vehicleIDs = ArrayList<Long>()
            for (entity in syncSet) {
                val parts = entity.split("-")
                userIDs.add(parts[0].toLong())
                vehicleIDs.add(parts[1].toLong())
            }
            lifecycle.coroutineScope.launch {
                withContext(Dispatchers.IO) {
                    viewModel.createCarAccident(
                        jwtToken, CarAccidentCreationRequest(
                            "none",
                            Date.valueOf(LocalDate.now().toString()),
                            Time.valueOf(LocalTime.now().toString()),
                            userIDs,
                            vehicleIDs
                        )
                    )
                }
            }
        }

    }

}