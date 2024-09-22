package com.example.aflamy.presentation.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.viewbinding.ViewBinding
import com.example.aflamy.R
import com.example.aflamy.databinding.FragmentSettingsBinding
import com.example.aflamy.presentation.ui.BaseFragment
import com.example.aflamy.presentation.ui.MainActivity
import com.example.domain.state.LocalUtil
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentSettingsBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        // Set up languages array
//        val languages = resources.getStringArray(R.array.language_options)
//        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, languages)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        binding.languageSpinner.adapter = adapter
//
//        // Set the spinner to the currently selected language
//        val currentLanguage = LocalUtil.getLang() ?: "en" // Default to English if no language set
//        binding.languageSpinner.setSelection(if (currentLanguage == "ar") 1 else 0)
//
//        // Handle language selection
//        binding.languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
//                val selectedLanguage = if (position == 1) "ar" else "en"
//
//                // Only change language if it's different from the current one
//                if (selectedLanguage != currentLanguage) {
//                    // Safely cast to MainActivity and change the language
//                    (activity as? MainActivity)?.let { mainActivity ->
//                        LocalUtil.setLocal(mainActivity, selectedLanguage)
//                        mainActivity.setLanguage(selectedLanguage) // Restart the activity to apply language change
//                    }
//                }
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>) {
//                // No action needed when nothing is selected
//            }
//        }
    }
}

