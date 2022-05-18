package com.petrov.vitaliy.caraccidentapp.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.petrov.vitaliy.caraccidentapp.R

class ProfileInfoFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.profile_info_fragment, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.RecyclerView)
        recyclerView.adapter = ProfileInfoRecyclerViewAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }
}