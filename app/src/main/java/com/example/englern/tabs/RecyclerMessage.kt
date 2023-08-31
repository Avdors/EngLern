package com.example.englern.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.englern.adapters.MessageAdapter
import com.example.englern.databinding.RecyclerMessageBinding
import com.example.englern.viewModels.ChatViewModel
import com.example.englern.viewModels.SharedViewModel


class RecyclerMessage : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val viewModel: ChatViewModel by activityViewModels()
    private lateinit var messageAdapter: MessageAdapter
    private var binding: RecyclerMessageBinding? = null
    private var them: String? = ""
    private var data: String? = ""
    private var itsDialog: Boolean? = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RecyclerMessageBinding.inflate(inflater, container, false)
        them = arguments?.getString("themName").toString()
        data = arguments?.getString("themData").toString()
        itsDialog = arguments?.getString("itsDialog").toBoolean()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val chatRecyclerView: RecyclerView = binding?.chatRecyclerView!!


        messageAdapter = MessageAdapter(mutableListOf())
        chatRecyclerView.adapter = messageAdapter
        chatRecyclerView.layoutManager = LinearLayoutManager(requireContext())


        // Observe the shared theme
        sharedViewModel.selectedTheme.observe(viewLifecycleOwner) { theme ->
            // Clear the messages and update UI when theme changes
            viewModel.clearMessages() // You'll need to implement this function
            messageAdapter.updateMessages(mutableListOf())
        }
//        viewModel.messages.observe(viewLifecycleOwner) { messages ->
//            messageAdapter.updateMessages(messages.toMutableList())
//            binding?.chatRecyclerView?.scrollToPosition(messages.size - 1)
//        }

        // Check if you have the required arguments

            // Handle other cases or other sources of messages
            viewModel.messages.observe(viewLifecycleOwner) { messages ->
                messageAdapter.updateMessages(messages.toMutableList())
                binding?.chatRecyclerView?.scrollToPosition(messages.size - 1)
            }

    }
}