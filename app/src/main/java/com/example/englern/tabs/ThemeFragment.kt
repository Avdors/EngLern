package com.example.englern.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.englern.R
import com.example.englern.databinding.FragmentThemeBinding
import com.example.englern.databinding.RecyclerMessageBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.*


class ThemeFragment : BottomSheetDialogFragment() {
    private var binding: FragmentThemeBinding? = null
    interface ThemeSelectionListener {
        fun onThemeSelected(theme: String,date:String)
    }

    private var listener: ThemeSelectionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThemeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val themes = resources.getStringArray(R.array.theme)
        val listView: ListView = binding?.themeListView!!
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        listView.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, themes)
        listView.setOnItemClickListener { _, _, position, _ ->
            listener?.onThemeSelected(themes[position],dateFormat.toString())
            dismiss()
        }
    }

    fun setThemeSelectionListener(listener: StartPage) {
        this.listener = listener
    }
}