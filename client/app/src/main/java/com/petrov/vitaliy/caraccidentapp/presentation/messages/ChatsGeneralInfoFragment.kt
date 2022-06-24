package com.petrov.vitaliy.caraccidentapp.presentation.messages

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.petrov.vitaliy.caraccidentapp.R
import com.petrov.vitaliy.caraccidentapp.injector
import com.petrov.vitaliy.caraccidentapp.presentation.messages.di.MessagesModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatsGeneralInfoFragment : Fragment() {

    private lateinit var module: MessagesModule
    private lateinit var viewModel: MessagesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        module = injector.messagesModule
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.chat_general_info_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.RecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val jwtToken = this.requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE)
            .getString("jwt", "null")!!

        val userRole = this.requireActivity().getSharedPreferences("userData", Context.MODE_PRIVATE)
            .getString("userRole", "")!!

        var userID: Long
        var addresseeID: Long

        val factory = MessageViewModelFactory(
            module.getOfficerChatsUseCase,
            module.getUserChatsUseCase,
            module.getAllChatMessagesUseCase,
            module.sendMessageUseCase,
            module.updateMessageUseCase
        )
        viewModel = ViewModelProvider(this, factory)[MessagesViewModel::class.java]

        viewModel.allChatGetState.observe(viewLifecycleOwner) {
            when (it) {
                is AllChatsGetState.Error -> {
                    Toast.makeText(
                        context,
                        "Не удалось загрузить информацию о чатах",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is AllChatsGetState.Success -> {
                    userID = when (userRole) {
                        "USER" -> it.data[0].carAccidentParticipantID
                        "TRAFFIC OFFICER" -> it.data[0].trafficPoliceOfficerID
                        else -> 0
                    }
                    addresseeID = when (userRole) {
                        "USER" -> it.data[0].trafficPoliceOfficerID
                        "TRAFFIC OFFICER" -> it.data[0].carAccidentParticipantID
                        else -> 0
                    }

                    this.requireContext()
                        .getSharedPreferences("userData", Context.MODE_PRIVATE).edit()
                        .putString("userID", userID.toString()).apply()
                    this.requireContext()
                        .getSharedPreferences("userData", Context.MODE_PRIVATE).edit()
                        .putString("addresseeID", addresseeID.toString()).apply()
                    val adapter = ChatsGeneralInfoRecyclerViewAdapter(userID)
                    recyclerView.adapter = adapter
                    adapter.submitList(it.data.toMutableList())
                    for (holder in adapter.getHolders()) {
                        holder.itemView.setOnClickListener {
                            this.requireContext()
                                .getSharedPreferences("userData", Context.MODE_PRIVATE).edit()
                                .putString("chatID", holder.chatID.toString()).apply()
                            val intent = Intent(context, SelectedChatActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
                else -> {

                }
            }
        }

        lifecycle.coroutineScope.launch {
            withContext(Dispatchers.IO) {
                if (userRole == "USER")
                    viewModel.getUserChats(jwtToken)
                else if (userRole == "TRAFFIC OFFICER")
                    viewModel.getOfficerChats(jwtToken)
            }
        }

        val bottomNavigation: BottomNavigationView =
            requireParentFragment().requireActivity().findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.messages -> {}
                R.id.user -> NavHostFragment.findNavController(this)
                    .navigate(R.id.action_chatsGeneralInfoFragment_to_profileInfoFragment)
                R.id.home -> NavHostFragment.findNavController(this)
                    .navigate(R.id.action_chatsGeneralInfoFragment_to_mainPageFragment)
            }
            true
        }
    }
}