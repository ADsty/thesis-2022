package com.petrov.vitaliy.caraccidentapp.presentation.accident.poll.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.petrov.vitaliy.caraccidentapp.R
import com.petrov.vitaliy.caraccidentapp.presentation.accident.statement.CarAccidentStatementActivity

class CarAccidentPollFragment11 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.poll_solution_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val question: TextView = view.findViewById(R.id.tv_question)
        question.text = "1.Зафиксируйте положение ТС и\n" +
                "предметов, имеющих отношение к ДТП\n" +
                " с помощью фотовидеосъемки\n" +
                " 2. Оформить ДТП в приложении\n" +
                " 3. Прибыть в отделение ГИБДД для\n" +
                "дальнейших действий"
        val question1: TextView = view.findViewById(R.id.tv_question1)
        question1.text = "Если вы не хотите следовать \n" +
                "предложенному решению, то\n" +
                "нажмите кнопку \n" +
                "“Завершить опрос”"
        val btnFirst: Button = view.findViewById(R.id.btn_first)
        btnFirst.text = "Оформить ДТП"
        btnFirst.setOnClickListener {
            val intent = Intent(context, CarAccidentStatementActivity::class.java)
            startActivity(intent)
        }
        val btnSecond: Button = view.findViewById(R.id.btn_second)
        btnSecond.text = "Завершить опрос"
        btnSecond.setOnClickListener {
            requireActivity().finish()
        }
    }

}