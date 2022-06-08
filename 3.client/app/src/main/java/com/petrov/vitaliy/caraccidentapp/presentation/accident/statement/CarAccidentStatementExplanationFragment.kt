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
import androidx.navigation.fragment.NavHostFragment
import com.petrov.vitaliy.caraccidentapp.R

class CarAccidentStatementExplanationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.car_accident_statement_explanation_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val explanationText: EditText = view.findViewById(R.id.explanation_text)
        val btnSend: Button = view.findViewById(R.id.btn_send)
        btnSend.setOnClickListener {
            if(explanationText.text.toString().isNotEmpty()) {
                this.requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE)
                    .edit().putString("explanationText", explanationText.text.toString()).apply()
                NavHostFragment.findNavController(this).navigate(R.id.action_carAccidentStatementExplanationFragment_to_carAccidentStatementSynchronizationFragment)
            }
            else {
                Toast.makeText(context, "Введите текст объяснения", Toast.LENGTH_SHORT).show()
            }
        }
    }
}