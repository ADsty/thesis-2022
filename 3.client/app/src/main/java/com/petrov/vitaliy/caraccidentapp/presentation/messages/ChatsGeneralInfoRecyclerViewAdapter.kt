package com.petrov.vitaliy.caraccidentapp.presentation.messages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.petrov.vitaliy.caraccidentapp.R
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.chats.ChatGetResponse

class ChatsGeneralInfoRecyclerViewAdapter(private val userID: Long) :
    ListAdapter<ChatGetResponse, ChatsGeneralInfoViewHolder>(ACCIDENT_EVENT_DIFF_UTIL) {

    private val holders = ArrayList<ChatsGeneralInfoViewHolder>()

    fun getHolders() = holders

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsGeneralInfoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_general_info_recycler_info_item, parent, false)
        return ChatsGeneralInfoViewHolder(view, userID)
    }

    override fun onBindViewHolder(holder: ChatsGeneralInfoViewHolder, position: Int) {
        holders.add(holder)
        holder.bind(getItem(position))
    }

    companion object {
        private val ACCIDENT_EVENT_DIFF_UTIL = object : DiffUtil.ItemCallback<ChatGetResponse>() {
            override fun areItemsTheSame(
                oldItem: ChatGetResponse,
                newItem: ChatGetResponse
            ): Boolean {
                return oldItem.chatID == newItem.chatID
            }

            override fun areContentsTheSame(
                oldItem: ChatGetResponse,
                newItem: ChatGetResponse
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}

class ChatsGeneralInfoViewHolder(itemView: View, private val userID: Long) :
    RecyclerView.ViewHolder(itemView) {
    private val mainInfo: TextView = itemView.findViewById(R.id.main_info)
    private val mainInfo1: TextView = itemView.findViewById(R.id.main_info1)
    private val timeInfo: TextView = itemView.findViewById(R.id.time_info)
    var chatID: Long = 0

    fun bind(chat: ChatGetResponse) {
        mainInfo.text = when (userID) {
            chat.lastMessage.senderID -> chat.lastMessage.addresseeFullName
            else -> chat.lastMessage.senderFullName
        }
        mainInfo1.text = chat.lastMessage.messageText.substring(0, 20)
        timeInfo.text =
            chat.lastMessage.messageCreationDate + "\n" + chat.lastMessage.messageCreationTime
        chatID = chat.chatID
    }
}