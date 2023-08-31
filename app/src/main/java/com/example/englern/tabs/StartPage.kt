package com.example.englern.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.englern.databinding.StartPageBinding
import com.example.englern.viewModels.SharedViewModel


class StartPage : Fragment(), ThemeFragment.ThemeSelectionListener {

    private var binding: StartPageBinding? = null
    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = StartPageBinding.inflate(inflater, container, false)

        binding?.imageTheme?.setOnClickListener{
          val bottomSheetDialogFragment = ThemeFragment()
          bottomSheetDialogFragment.setThemeSelectionListener(this)
          bottomSheetDialogFragment.show(childFragmentManager, bottomSheetDialogFragment.tag)
        }

        return binding?.root
    }
    override fun onThemeSelected(theme: String, data: String) {
        // Handle the selected theme
        binding?.instLogo?.visibility = View.GONE

        // Show the text view and update with the selected theme
        binding?.textTheme?.apply {
            text = theme
            visibility = View.VISIBLE

        }
        sharedViewModel.updateSelectedTheme(theme,data)
    }
}