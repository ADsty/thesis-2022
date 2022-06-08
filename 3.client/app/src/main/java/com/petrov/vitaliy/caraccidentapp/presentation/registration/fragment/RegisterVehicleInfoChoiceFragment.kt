package com.petrov.vitaliy.caraccidentapp.presentation.registration.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.petrov.vitaliy.caraccidentapp.R
import com.petrov.vitaliy.caraccidentapp.presentation.mainpage.MainPageActivity

class RegisterVehicleInfoChoiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.register_vehicle_info_add_choice_fragment,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonFirst: Button = view.findViewById(R.id.btn_first)
        buttonFirst.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_registerVehicleInfoChoiceFragment_to_registerVehicleInsuranceInfoFragment)
        }
        val buttonSecond: Button = view.findViewById(R.id.btn_second)
        buttonSecond.setOnClickListener {
            this.requireActivity().getSharedPreferences("auth", Context.MODE_PRIVATE).edit()
                .putBoolean("isLoggedIn", true).apply()
            val intent = Intent(context, MainPageActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}