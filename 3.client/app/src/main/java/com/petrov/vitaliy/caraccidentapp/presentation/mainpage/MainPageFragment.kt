package com.petrov.vitaliy.caraccidentapp.presentation.mainpage

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.petrov.vitaliy.caraccidentapp.R
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.accident.CarAccidentEntityGetResponse
import com.petrov.vitaliy.caraccidentapp.injector
import com.petrov.vitaliy.caraccidentapp.presentation.accident.poll.CarAccidentPollActivity
import com.petrov.vitaliy.caraccidentapp.presentation.mainpage.di.MainPageModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Date
import java.time.LocalDate

class MainPageFragment : Fragment() {

    private val adapter: AccidentEventsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        AccidentEventsAdapter()
    }

    private lateinit var module: MainPageModule
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var tvErrorText: TextView
    private lateinit var viewModel: MainPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        module = injector.mainPageModule
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_page_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dateView: TextView = view.findViewById(R.id.date_view)
        var currentDate = LocalDate.now()
        var userRole = ""
        dateView.text = currentDate.toString()
        recyclerView = view.findViewById(R.id.rv_events)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        progressBar = view.findViewById(R.id.progress_bar)
        tvErrorText = view.findViewById(R.id.error_text)

        val jwtToken = this.requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE)
            .getString("jwt", "null")!!

        var accidents: Array<CarAccidentEntityGetResponse> = arrayOf()
        val factory = MainPageViewModelFactory(module.getUserRoleUseCase, module.getAllUserCarAccidents, module.getAllOfficerCarAccidentsUseCase, module.getAllCarAccidentsChangelogsUseCase)
        viewModel = ViewModelProvider(this, factory)[MainPageViewModel::class.java]
        viewModel.changelogState.observe(viewLifecycleOwner) {
            when (it) {
                is ChangelogState.ChangelogData -> {
                    adapter.submitList(it.data.toMutableList())
                }
                is ChangelogState.Error -> {
                    Toast.makeText(context, "Не удалось загрузить информацию по одному из ДТП", Toast.LENGTH_SHORT).show()
                }
                else -> {

                }
            }
        }

        viewModel.mainPageState.observe(viewLifecycleOwner) {
            when(it) {
                is MainPageState.Loading -> {
                    recyclerView.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                    tvErrorText.visibility = View.GONE
                }
                is MainPageState.Error -> {
                    recyclerView.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    tvErrorText.visibility = View.VISIBLE
                    tvErrorText.text = "Не удалось загрузить информацию о ваших ДТП"
                }
                is MainPageState.UserRoleGot -> {
                    this.requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE)
                        .edit().putString("userRole", it.userRole).apply()
                    userRole = it.userRole
                    setButtons(userRole)
                }
                is MainPageState.CarAccidentData -> {

                    recyclerView.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    tvErrorText.visibility = View.GONE

                    accidents = it.data

                    for(accident: CarAccidentEntityGetResponse in it.data) {
                        lifecycle.coroutineScope.launch {
                            withContext(Dispatchers.IO) {
                                viewModel.getAllCarAccidentsChangelogs(jwtToken, accident.carAccidentEntityID, Date.valueOf(currentDate.toString()))
                            }
                        }
                    }
                }
                else -> {
                    recyclerView.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    tvErrorText.visibility = View.GONE
                }
            }
        }

        lifecycle.coroutineScope.launch {
            withContext(Dispatchers.IO) {
                viewModel.getUserRole(jwtToken)
                if(userRole == "USER")
                    viewModel.getAllUserCarAccidents(jwtToken)
                else if(userRole == "TRAFFIC OFFICER")
                    viewModel.getAllOfficerCarAccidents(jwtToken)
            }
        }


        val rightArrow: ImageView = view.findViewById(R.id.right_arrow)
        rightArrow.setOnClickListener{
            currentDate = currentDate.plusDays(1)
            dateView.text = currentDate.toString()
            for(accident in accidents) {
                lifecycle.coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        viewModel.getAllCarAccidentsChangelogs(jwtToken, accident.carAccidentEntityID, Date.valueOf(currentDate.toString()))
                    }
                }
            }
        }

        val leftArrow: ImageView = view.findViewById(R.id.left_arrow)
        leftArrow.setOnClickListener{
            currentDate = currentDate.minusDays(1)
            dateView.text = currentDate.toString()
            for(accident in accidents) {
                lifecycle.coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        viewModel.getAllCarAccidentsChangelogs(jwtToken, accident.carAccidentEntityID, Date.valueOf(currentDate.toString()))
                    }
                }
            }
        }

        val bottomNavigation: BottomNavigationView =
            requireParentFragment().requireActivity().findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.messages -> NavHostFragment.findNavController(this)
                    .navigate(R.id.action_mainPageFragment_to_chatsGeneralInfoFragment)
                R.id.user -> NavHostFragment.findNavController(this)
                    .navigate(R.id.action_mainPageFragment_to_profileInfoFragment)
                R.id.home -> {}
            }
            true
        }
    }

    private fun setButtons(userRole: String) {
        val accidentReportButton: Button = requireView().findViewById(R.id.accident_report_button)
        if(userRole == "USER") {
            accidentReportButton.setOnClickListener {
                val intent = Intent(context, CarAccidentPollActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
                startActivity(intent)
            }
        }
        else if(userRole == "TRAFFIC OFFICER") {
            accidentReportButton.text = "Изменить статус"
        }
    }

}