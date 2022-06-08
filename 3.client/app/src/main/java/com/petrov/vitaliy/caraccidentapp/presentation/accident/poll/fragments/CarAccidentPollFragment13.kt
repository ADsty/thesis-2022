package com.petrov.vitaliy.caraccidentapp.presentation.accident.poll.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.petrov.vitaliy.caraccidentapp.R

class CarAccidentPollFragment13 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.poll_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val question: TextView = view.findViewById(R.id.tv_question)
        question.text = "Необходима экстренная доставка\n" +
                "пострадавшего в больницу \n" +
                "Вашей машиной?"
        val btnFirst: Button = view.findViewById(R.id.btn_first)
        btnFirst.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_carAccidentPollFragment13_to_carAccidentPollFragment14)
        }
        val btnSecond: Button = view.findViewById(R.id.btn_second)
        btnSecond.setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_carAccidentPollFragment13_to_carAccidentPollFragment15)
        }
    }

}