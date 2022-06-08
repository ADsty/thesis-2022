package com.petrov.vitaliy.caraccidentapp.presentation.accident.statement

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.petrov.vitaliy.caraccidentapp.R
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.DriverLicenseUpdateRequest
import com.petrov.vitaliy.caraccidentapp.injector
import com.petrov.vitaliy.caraccidentapp.presentation.accident.statement.di.CarAccidentStatementModule
import com.petrov.vitaliy.caraccidentapp.presentation.registration.fragment.RegisterInfoRecyclerViewAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Date

class CarAccidentStatementDriverLicenseFragment : Fragment() {
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
        val title: TextView = view.findViewById(R.id.tv_register_title)
        val subtitle: TextView = view.findViewById(R.id.tv_register_subtitle)

        title.text = "Заполните информацию о \n ваших водительских правах\n" +
                "(если таковые имеются)"

        subtitle.text = "При отсутствии водительских прав\n" +
                "не заполняйте поля на данном экране\n" +
                "и нажмите на кнопку\n" +
                "“подтвердить ввод данных”"


        val recyclerView: RecyclerView = view.findViewById(R.id.rv_fields)
        hints.add("Номер удостоверения")
        hints.add("Дата выдачи")
        hints.add("Категория")
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

        viewModel.getDriverLicenseState.observe(viewLifecycleOwner) {
            when (it) {
                is StatementGetDriverLicenseState.Error -> {
                    Toast.makeText(
                        context,
                        "Не удалось получить данные",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is StatementGetDriverLicenseState.Success -> {
                    list[0].textInput.setText(it.data.driverLicenseNumber)
                    list[1].textInput.setText(it.data.driverLicenseCategory)
                    list[2].textInput.setText(it.data.driverLicenseDateOfIssue)
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
                    NavHostFragment.findNavController(this).navigate(R.id.action_carAccidentStatementDriverLicenseFragment_to_carAccidentStatementGeneralInfoFragment)
                }
                else -> {

                }
            }
        }

        lifecycle.coroutineScope.launch {
            withContext(Dispatchers.IO) {
                viewModel.getDriverLicense(jwtToken)
            }
        }

        val button: Button = view.findViewById(R.id.btn_submit)


        button.setOnClickListener {
            lifecycle.coroutineScope.launch {
                withContext(Dispatchers.IO) {
                    viewModel.updateDriverLicense(
                        jwtToken,
                        DriverLicenseUpdateRequest(
                            list[0].textInput.text.toString(),
                            list[1].textInput.text.toString(),
                            Date.valueOf(list[2].textInput.text.toString()),
                        )
                    )
                }
            }
        }
    }
}