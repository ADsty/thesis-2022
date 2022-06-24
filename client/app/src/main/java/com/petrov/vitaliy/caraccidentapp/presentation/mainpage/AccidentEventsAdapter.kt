package com.petrov.vitaliy.caraccidentapp.presentation.mainpage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.petrov.vitaliy.caraccidentapp.R
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.accident.CarAccidentEntityChangelogGetResponse

class AccidentEventsAdapter :
    ListAdapter<CarAccidentEntityChangelogGetResponse, AccidentEventViewHolder>(ACCIDENT_EVENT_DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccidentEventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_page_recycler_view_item, parent, false)
        return AccidentEventViewHolder(view)
    }

    override fun onBindViewHolder(holder: AccidentEventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val ACCIDENT_EVENT_DIFF_UTIL = object : DiffUtil.ItemCallback<CarAccidentEntityChangelogGetResponse>() {
            override fun areItemsTheSame(oldItem: CarAccidentEntityChangelogGetResponse, newItem: CarAccidentEntityChangelogGetResponse): Boolean {
                return oldItem.carAccidentEntityChangelogID == newItem.carAccidentEntityChangelogID
            }

            override fun areContentsTheSame(
                oldItem: CarAccidentEntityChangelogGetResponse,
                newItem: CarAccidentEntityChangelogGetResponse
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}

class AccidentEventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val mainInfo: TextView = itemView.findViewById(R.id.main_info)
    private val timeInfo: TextView = itemView.findViewById(R.id.time_info)

    fun bind(event: CarAccidentEntityChangelogGetResponse) {
        mainInfo.text = event.changeDescription
        timeInfo.text = event.changeTime
    }
}
