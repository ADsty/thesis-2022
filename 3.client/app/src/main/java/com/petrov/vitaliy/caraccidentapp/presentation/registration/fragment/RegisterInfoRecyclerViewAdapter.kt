package com.petrov.vitaliy.caraccidentapp.presentation.registration.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.petrov.vitaliy.caraccidentapp.R

class RegisterInfoRecyclerViewAdapter(private val hints: ArrayList<String>) :
    RecyclerView.Adapter<RegisterInfoRecyclerViewAdapter.ViewHolder>() {

    private val holders = ArrayList<RegisterInfoRecyclerViewAdapter.ViewHolder>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_text_field, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textInput.hint = hints[position]
        holders.add(holder)
    }

    override fun getItemCount(): Int {
        return hints.size
    }

    fun getHolders() = holders

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textInput: EditText = view.findViewById(R.id.editText)
    }
}