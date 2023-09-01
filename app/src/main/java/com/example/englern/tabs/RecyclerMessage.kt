package com.example.englern.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.englern.adapters.MessageAdapter
import com.example.englern.databinding.RecyclerMessageBinding
import com.example.englern.viewModels.SharedViewModel
import com.example.englern.viewModels.WindowViewModel


class RecyclerMessage : Fragment() {
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val viewModel: WindowViewModel by activityViewModels()
    private lateinit var messageAdapter: MessageAdapter
    private var binding: RecyclerMessageBinding? = null
//   private var them: String? = ""
//   private var data: String? = ""
//  private var itsDialog: Boolean? = false

//    //для реализации румм
//    private var messRepository: MessRepository? = null
//    private var messViewModel: ChatViewModel? = null
//    private var messFactory: MessFactory? = null
//    private var messAdapter: MessAdapterRoom? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RecyclerMessageBinding.inflate(inflater, container, false)

//    val messDao = Database.getInstance((context as FragmentActivity).application).messDao
//        messRepository = MessRepository(messDao)
//        messFactory = MessFactory(messRepository!!)
//        messViewModel = ViewModelProvider(this, messFactory!!).get(ChatViewModel::class.java)

        //initRecyclerFilterMess()
       // displayFilterMess()
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
            messageAdapter.notifyDataSetChanged()
        }

        viewModel.messages.observe(viewLifecycleOwner) { messages ->
            messageAdapter.updateMessages(messages.toMutableList())
            messageAdapter.notifyDataSetChanged()
            binding?.chatRecyclerView?.scrollToPosition(messages.size - 1)
        }

//        sharedViewModel.selectedTheme.observe(viewLifecycleOwner) { theme ->
//
//            them = theme
//            data = sharedViewModel.currentDateTime.value
//
//            displayFilterMess()
//
//        }
//
//
//
//        messViewModel?.messages?.observe(viewLifecycleOwner, Observer { messages ->
//            messAdapter?.setList(messages)
//            messAdapter?.notifyDataSetChanged()
//            binding?.chatRecyclerView?.scrollToPosition(messages.size - 1)
//        })


    }

    // код для рум вывод списка сообщений по фильтру
//    private fun initRecyclerFilterMess() {
//        binding?.chatRecyclerView?.layoutManager = LinearLayoutManager(context)
//        messAdapter = MessAdapterRoom()
//        binding?.chatRecyclerView?.adapter = messAdapter
//
//        displayFilterMess()
//    }
//
//    private fun displayFilterMess() {
//        if (them != null && data != null) {
//            messViewModel?.getFilter(them!!, data!!)
//                ?.observe(viewLifecycleOwner, Observer {
//                    Log.d("DatabaseData", "Received data: ${it.joinToString()}")
//                    messAdapter?.setList(it)
//                    messAdapter?.notifyDataSetChanged()
//
//                })
//        }
//    }


}