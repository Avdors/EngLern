package com.example.englern.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.englern.R
import com.example.englern.models.MessageModel

class MessageAdapter(private val messages: MutableList<MessageModel>) :
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    companion object {
        const val VIEW_TYPE_USER = 1
        const val VIEW_TYPE_PROGRAM = 2
    }

    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textMessage: TextView = itemView.findViewById(R.id.textMessage)
        val timeMessage: TextView = itemView.findViewById(R.id.timeMessage)
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].isUserMessage) VIEW_TYPE_USER else VIEW_TYPE_PROGRAM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val layout = if (viewType == VIEW_TYPE_USER) R.layout.user_message_item else R.layout.program_message_item
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.textMessage.text = message.text
        holder.timeMessage.text = message.time
    }

    override fun getItemCount() = messages.size

    fun addMessage(message: MessageModel) {
        messages.add(message)
        notifyItemInserted(messages.size - 1)
    }

    fun updateMessages(newMessages: MutableList<MessageModel>) {
        messages.clear()
        messages.addAll(newMessages)

    }

    fun setList(newMessages: MutableList<MessageModel>) {
        messages.clear()
        messages.addAll(newMessages)

    }
}