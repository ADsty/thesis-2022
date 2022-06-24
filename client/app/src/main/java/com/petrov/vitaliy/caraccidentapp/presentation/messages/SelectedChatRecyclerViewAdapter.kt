package com.petrov.vitaliy.caraccidentapp.presentation.messages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.petrov.vitaliy.caraccidentapp.R
import com.petrov.vitaliy.caraccidentapp.domain.models.responses.chats.MessageGetResponse

class SelectedChatRecyclerViewAdapter(private val userID: Long, private val messageSenderList: ArrayList<Long>) :
    ListAdapter<MessageGetResponse, MessagesViewHolder>(ACCIDENT_EVENT_DIFF_UTIL) {

    private val holders = ArrayList<MessagesViewHolder>()

    fun getHolders() = holders

    override fun getItemViewType(position: Int): Int {
        return if(messageSenderList[position] == userID)
            0
        else
            1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesViewHolder {
        val view = when(viewType) {
            0 -> LayoutInflater.from(parent.context).inflate(R.layout.item_current_user_message, parent, false)
            else -> LayoutInflater.from(parent.context)
                .inflate(R.layout.item_other_message, parent, false)
        }
        return MessagesViewHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: MessagesViewHolder, position: Int) {
        holders.add(holder)
        holder.bind(getItem(position))
    }

    companion object {
        private val ACCIDENT_EVENT_DIFF_UTIL = object : DiffUtil.ItemCallback<MessageGetResponse>() {
            override fun areItemsTheSame(
                oldItem: MessageGetResponse,
                newItem: MessageGetResponse
            ): Boolean {
                return oldItem.chatID == newItem.chatID
            }

            override fun areContentsTheSame(
                oldItem: MessageGetResponse,
                newItem: MessageGetResponse
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}

class MessagesViewHolder(itemView: View, private val type: Int) :
    RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.findViewById(R.id.name)
    private val messageBody: TextView = itemView.findViewById(R.id.message_body)
    private val tvTime: TextView = itemView.findViewById(R.id.tv_time)

    fun bind(message: MessageGetResponse) {
        messageBody.text = message.messageText
        tvTime.text = message.messageCreationDate + "\n" + message.messageCreationTime
        if(type == 1){
            name.text = message.senderFullName
        }
    }
}