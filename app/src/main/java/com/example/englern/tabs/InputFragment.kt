package com.example.englern.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.activityViewModels
import com.example.englern.databinding.FragmentInputBinding
import com.example.englern.viewModels.ChatViewModel
import com.example.englern.models.Message
import com.example.englern.models.SharedTheme
import java.text.SimpleDateFormat
import java.util.*


class InputFragment : Fragment() {
    private val viewModel: ChatViewModel by activityViewModels()
    private var binding: FragmentInputBinding? = null
    private val theme = SharedTheme.selectedTheme
    private val currentData = SharedTheme.currentDateTime
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInputBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sendMessage: ImageButton = binding?.sendMessage!!
        val inputMessage: EditText = binding?.inputMessage!!

        sendMessage.setOnClickListener {

            val messageText = inputMessage.text.toString()

            val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

            val userMessage = Message(messageText, true, currentTime, theme.toString())

            viewModel.addMessage(userMessage)


            val replyMessage = Message("I received your message", false, currentTime, theme.toString())
            viewModel.addMessage(replyMessage)

            inputMessage.text.clear()
        }
    }
}