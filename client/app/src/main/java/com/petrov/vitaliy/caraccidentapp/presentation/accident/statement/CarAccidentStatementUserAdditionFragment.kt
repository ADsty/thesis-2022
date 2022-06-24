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
import com.petrov.vitaliy.caraccidentapp.R

class CarAccidentStatementUserAdditionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.share_code_addition_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button: Button = view.findViewById(R.id.button)
        val editText: EditText = view.findViewById(R.id.editTextPhone)
        button.setOnClickListener {
            if(editText.text.toString().isNotEmpty()) {
                val synchronizedUsers = this.requireActivity().getSharedPreferences("synchronizeUsers", Context.MODE_PRIVATE)
                    .getStringSet("sync", setOf())!!
                this.requireActivity().getSharedPreferences("synchronizeUsers", Context.MODE_PRIVATE)
                    .edit().putStringSet("sync", synchronizedUsers + editText.text.toString()).apply()
            }
            else {
                Toast.makeText(context, "Введите номер участника", Toast.LENGTH_SHORT).show()
            }
        }
    }
}