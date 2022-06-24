package com.petrov.vitaliy.caraccidentapp.presentation.accident.poll.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.petrov.vitaliy.caraccidentapp.R
import com.petrov.vitaliy.caraccidentapp.presentation.accident.statement.CarAccidentStatementActivity

class CarAccidentPollOfferFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.poll_offer_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnFirst: Button = view.findViewById(R.id.btn_first)
        btnFirst.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_carAccidentPollOfferFragment_to_carAccidentPollFragment1)
        }
        val btnSecond: Button = view.findViewById(R.id.btn_second)
        btnSecond.setOnClickListener {
            val intent = Intent(context, CarAccidentStatementActivity::class.java)
            startActivity(intent)
        }
    }
}