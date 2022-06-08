package com.petrov.vitaliy.caraccidentapp.presentation.profile

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.petrov.vitaliy.caraccidentapp.R
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.user.DriverLicenseUpdateRequest
import com.petrov.vitaliy.caraccidentapp.injector
import com.petrov.vitaliy.caraccidentapp.presentation.profile.di.ProfileModule
import com.petrov.vitaliy.caraccidentapp.presentation.registration.fragment.RegisterInfoRecyclerViewAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Date

class ProfileDriverLicenseChangeActivity : AppCompatActivity() {

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

        val title: TextView = findViewById(R.id.tv_register_title)
        val subtitle: TextView = findViewById(R.id.tv_register_subtitle)

        title.text = "Заполните информацию о \n ваших водительских правах\n" +
                "(если таковые имеются)"

        subtitle.text = "При отсутствии водительских прав\n" +
                "не заполняйте поля на данном экране\n" +
                "и нажмите на кнопку\n" +
                "“подтвердить ввод данных”"


        val recyclerView: RecyclerView = findViewById(R.id.rv_fields)
        hints.add("Номер удостоверения")
        hints.add("Дата выдачи")
        hints.add("Категория")
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

        viewModel.getDriverLicenseState.observe(this) {
            when (it) {
                is ProfileGetDriverLicenseState.Error -> {
                    Toast.makeText(
                        this,
                        "Не удалось получить данные",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is ProfileGetDriverLicenseState.Success -> {
                    list[0].textInput.setText(it.data.driverLicenseNumber)
                    list[1].textInput.setText(it.data.driverLicenseCategory)
                    list[2].textInput.setText(it.data.driverLicenseDateOfIssue)
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
                viewModel.getDriverLicense(jwtToken)
            }
        }

        val button: Button = findViewById(R.id.btn_submit)


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