package com.example.englern.tabs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.englern.data.Database
import com.example.englern.databinding.FragmentInputBinding
import com.example.englern.models.MessageApi
import com.example.englern.models.MessageModel
import com.example.englern.models.SharedTheme
import com.example.englern.repositories.MessRepository
import com.example.englern.viewModels.*
import java.text.SimpleDateFormat
import java.util.*

//, View.OnClickListener
class InputFragment : Fragment() {
    //Реализация румм
    private val viewModel: WindowViewModel by activityViewModels()
    private var messRepository: MessRepository? = null
    private var messViewModel: ChatViewModel? = null
    private var messFactory: MessFactory? = null
    //api
    private var apiMessage: MessageApi? = null
    private val apiChatViewModel: ApiChatViewModel by activityViewModels()
  //  private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var sharedViewModel: SharedViewModel
    private var binding: FragmentInputBinding? = null
  //  private val theme = SharedTheme.selectedTheme
    private var theme: String? = null
  // private val dateThem = SharedTheme.currentDateTime
    private var dateThem: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInputBinding.inflate(inflater, container, false)
        val messDao = Database.getInstance((context as FragmentActivity).application).messDao
        messRepository = MessRepository(messDao)
        messFactory = MessFactory(messRepository!!)
        messViewModel = ViewModelProvider(this, messFactory!!).get(ChatViewModel::class.java)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        //binding?.sendMessage?.setOnClickListener(this)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sendMessage: ImageButton = binding?.sendMessage!!
        val inputMessage: EditText = binding?.inputMessage!!

        sharedViewModel.selectedTheme.observe(viewLifecycleOwner, { selectedTheme ->
        theme = selectedTheme
    })

        sharedViewModel.currentDateTime.observe(viewLifecycleOwner, { dateTime ->
        dateThem = dateTime
        sharedViewModel.clearCurrentMessages()
    })

        sendMessage.setOnClickListener {

            val messageText = inputMessage.text.toString()

            val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

            val userMessage = MessageModel(0, messageText, 1, currentTime, theme.toString(), dateThem.toString())

            //api
            val currentMessages = sharedViewModel.currentMessages.value
            val responseLiveData = apiChatViewModel.sendMessagesAndGetResponse(currentMessages!!)


            responseLiveData.observe(viewLifecycleOwner){response ->
                val serverReply = MessageModel(0, response.message, 0, SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()), theme.toString(), dateThem.toString())

                val apiResponseMessage = MessageApi("server", response.message)
                sharedViewModel.addMessageToCurrentMessages(apiResponseMessage)

                messViewModel?.startInsert(response.message, false, currentTime, theme.toString(), dateThem.toString())
            }

            viewModel.addMessage(userMessage)
            //сообщение для апи
          //  apiMessage = MessageApi("user", messageText)
         //   sharedViewModel.addMessageToCurrentMessages(apiMessage!!)

            val replyMessage = MessageModel(0, "The answer is being printed...", 0, currentTime, theme.toString(), dateThem.toString())
            viewModel.addMessage(replyMessage)
            onClickWrite()
            inputMessage.text.clear()
        }
    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//    sharedViewModel.selectedTheme.observe(viewLifecycleOwner, { selectedTheme ->
//        theme = selectedTheme
//    })
//
//    sharedViewModel.currentDateTime.observe(viewLifecycleOwner, { dateTime ->
//        dateThem = dateTime
//    })
//
//    }


    fun onClickWrite() {

        val inputMessage: EditText = binding?.inputMessage!!
        val messageText = inputMessage.text.toString()

        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

       // val userMessage = MessageModel(0, messageText, true, currentTime, theme.toString(), dateThem.toString())
        messViewModel?.startInsert(messageText, true, currentTime, theme.toString(), dateThem.toString())
        val result = messViewModel?.getFilter(theme.toString(), dateThem.toString())
        Log.d("DatabaseData", "Input data: ${result?.value?.joinToString()}")
       // messViewModel?.startInsert("I received your message", false, currentTime, theme.toString(), dateThem.toString())
        inputMessage.text.clear()
      //  dismiss()

       // (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(R.id.content, TabProducts()).commit()
    }
}