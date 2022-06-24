package com.petrov.vitaliy.caraccidentapp.presentation.messages

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.petrov.vitaliy.caraccidentapp.R
import com.petrov.vitaliy.caraccidentapp.domain.models.requests.chats.MessageCreationRequest
import com.petrov.vitaliy.caraccidentapp.injector
import com.petrov.vitaliy.caraccidentapp.presentation.messages.di.MessagesModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.Date
import java.sql.Time
import java.time.LocalDate
import java.time.LocalTime

class SelectedChatActivity : AppCompatActivity() {

    private lateinit var module: MessagesModule
    private lateinit var viewModel: MessagesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_fragment)
        module = injector.messagesModule
    }

    override fun onResume() {
        super.onResume()
        val recyclerView: RecyclerView = findViewById(R.id.rv_messages)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val jwtToken = this.getSharedPreferences("userData", Context.MODE_PRIVATE)
            .getString("jwt", "null")!!
        val chatID = this.getSharedPreferences("userData", Context.MODE_PRIVATE)
            .getString("chatID", "null")!!
        val userID = this.getSharedPreferences("userData", Context.MODE_PRIVATE)
            .getString("userID", "")!!
        val addresseeID = this.getSharedPreferences("userData", Context.MODE_PRIVATE)
            .getString("addresseeID", "")!!

        val factory = MessageViewModelFactory(
            module.getOfficerChatsUseCase,
            module.getUserChatsUseCase,
            module.getAllChatMessagesUseCase,
            module.sendMessageUseCase,
            module.updateMessageUseCase
        )

        viewModel = ViewModelProvider(this, factory)[MessagesViewModel::class.java]

        viewModel.allMessageGetState.observe(this) {
            when (it) {
                is AllMessageGetState.Error -> {
                    Toast.makeText(
                        this,
                        "Не удалось загрузить информацию о чате",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is AllMessageGetState.Success -> {
                    val senderList = ArrayList<Long>()
                    for (message in it.data) {
                        senderList.add(message.senderID)
                    }
                    val adapter = SelectedChatRecyclerViewAdapter(userID.toLong(), senderList)
                    recyclerView.adapter = adapter
                    adapter.submitList(it.data.toMutableList())
                }
                else -> {

                }
            }
        }

        viewModel.messageState.observe(this) {
            when (it) {
                is MessageState.Error -> {
                    Toast.makeText(
                        this,
                        "Не удалось отправить сообщение",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is MessageState.Sent -> {
                    viewModel.changeMessageState()
                    lifecycle.coroutineScope.launch {
                        withContext(Dispatchers.IO) {
                            viewModel.getAllChatMessages(jwtToken, chatID.toLong())
                        }
                    }
                }
                else -> {

                }
            }
        }

        lifecycle.coroutineScope.launch {
            withContext(Dispatchers.IO) {
                viewModel.getAllChatMessages(jwtToken, chatID.toLong())
            }
        }

        val messageText: EditText = findViewById(R.id.et_message)

        val button: ImageView = findViewById(R.id.btn_send)
        button.setOnClickListener {
            if (messageText.text.toString().isNotEmpty()) {
                lifecycle.coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        viewModel.sendMessage(
                            jwtToken, MessageCreationRequest(
                                addresseeID.toLong(),
                                Date.valueOf(LocalDate.now().toString()),
                                Time.valueOf(LocalTime.now().toString()),
                                messageText.text.toString(),
                                chatID.toLong()
                            )
                        )
                    }
                }
            }
        }

    }

}