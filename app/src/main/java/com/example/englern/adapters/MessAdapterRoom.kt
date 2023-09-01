package com.example.englern.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.englern.databinding.ProgramMessageItemBinding
import com.example.englern.databinding.UserMessageItemBinding
import com.example.englern.models.MessageModel
import com.example.englern.viewModels.ChatViewModel
import java.nio.file.attribute.UserPrincipal

class MessAdapterRoom: RecyclerView.Adapter<MessAdapterRoom.MessHolder>() {

    private val messList = ArrayList<MessageModel>()

    companion object {
        const val VIEW_TYPE_USER = 1
        const val VIEW_TYPE_PROGRAM = 2
    }
    sealed class MessageBinding {
        data class UserBinding(val binding: UserMessageItemBinding) : MessageBinding()
        data class ProgramBinding(val binding: ProgramMessageItemBinding) : MessageBinding()
    }
    class MessHolder(val messageBinding: MessageBinding) : RecyclerView.ViewHolder(
        when (messageBinding) {
            is MessageBinding.UserBinding -> messageBinding.binding.root
            is MessageBinding.ProgramBinding -> messageBinding.binding.root
        }
    ) {
        fun bind(message: MessageModel) {
            when (messageBinding) {
                is MessageBinding.UserBinding -> {
                    messageBinding.binding.textMessage.text = message.text
                    messageBinding.binding.timeMessage.text = message.time
                }
                is MessageBinding.ProgramBinding -> {
                    messageBinding.binding.textMessage.text = message.text
                    messageBinding.binding.timeMessage.text = message.time
                }
            }
        }

    }
    override fun getItemViewType(position: Int): Int {
        return if (messList[position].isUserMessage == 1) MessageAdapter.VIEW_TYPE_USER else MessageAdapter.VIEW_TYPE_PROGRAM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val messageBinding = when (viewType) {
            VIEW_TYPE_USER -> {
                val userBinding = UserMessageItemBinding.inflate(layoutInflater, parent, false)
                MessageBinding.UserBinding(userBinding)
            }
            else -> {
                val programBinding = ProgramMessageItemBinding.inflate(layoutInflater, parent, false)
                MessageBinding.ProgramBinding(programBinding)
            }
        }
        return MessHolder(messageBinding)
    }

    override fun onBindViewHolder(holder: MessHolder, position: Int) {
        holder.bind(messList[position])
    }

    override fun getItemCount(): Int {
        return messList.size
    }
    fun setList(message: List<MessageModel>) {
        messList.clear()
        messList.addAll(message)

    }
}