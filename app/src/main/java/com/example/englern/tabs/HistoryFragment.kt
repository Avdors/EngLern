package com.example.englern.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.englern.R
import com.example.englern.adapters.MessAdapterRoom
import com.example.englern.data.Database
import com.example.englern.databinding.FragmentHistoryBinding
import com.example.englern.repositories.MessRepository
import com.example.englern.viewModels.ChatViewModel
import com.example.englern.viewModels.MessFactory


class HistoryFragment : Fragment() {
    private var binding: FragmentHistoryBinding? = null
    private var messRepository: MessRepository? = null
    private var messViewModel: ChatViewModel? = null
    private var messFactory: MessFactory? = null
    private var messAdapter: MessAdapterRoom? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        val messDao = Database.getInstance((context as FragmentActivity).application).messDao
        messRepository = MessRepository(messDao)
        messFactory = MessFactory(messRepository!!)
        messViewModel = ViewModelProvider(this, messFactory!!).get(ChatViewModel::class.java)
        initRecyclerFilterMess()
        return binding?.root
    }

    private fun initRecyclerFilterMess() {
        binding?.chatRecyclerView?.layoutManager = LinearLayoutManager(context)
        messAdapter = MessAdapterRoom()
        binding?.chatRecyclerView?.adapter = messAdapter

       displayFilterMess()
     //   testFilterMess()
    }

    private fun displayFilterMess() {
        messViewModel?.message?.observe(viewLifecycleOwner, Observer {
        messAdapter?.setList(it)
            messAdapter?.notifyDataSetChanged()
        })
    }

    private fun testFilterMess() {
        val them = "Dating a girl"
        val data = "2023/09/30 19:03:31"
        messViewModel?.getFilter(them!!, data!!)?.observe(viewLifecycleOwner, Observer {
            messAdapter?.setList(it)
            messAdapter?.notifyDataSetChanged()
        })
    }

}