package com.petrov.vitaliy.caraccidentapp.presentation.mainpage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.petrov.vitaliy.caraccidentapp.R

class MainPageRecyclerViewAdapter(): RecyclerView.Adapter<MainPageRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_page_recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position == 0) {
            holder.main_info.text = "Статус вашего заявления №55226225 был изменен"
            holder.time_info.text = "16:20"
        }
        else {
            holder.main_info.text = "В дело о ДТП, связанное с вашим заявлением №55226225 был добавлен новый документ"
            holder.time_info.text = "16:20"
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val main_info: TextView = view.findViewById(R.id.main_info)
        val time_info: TextView = view.findViewById(R.id.time_info)
    }
}