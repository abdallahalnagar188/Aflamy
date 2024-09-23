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

        setupLanguageSpinner()
    }

    private fun setupLanguageSpinner() {
        // Get the languages array
        val languages = resources.getStringArray(R.array.language_options)

        // Initialize ArrayAdapter for the spinner
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, languages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.languageSpinner.adapter = adapter

        // Retrieve the current language
        val currentLanguage = LocalUtil.getLang() ?: "en"  // Default to "en" if no language is set

        // Set the spinner to the currently selected language
        binding.languageSpinner.setSelection(if (currentLanguage == "ar") 1 else 0)

        // Handle spinner item selection
        binding.languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedLanguage = if (position == 1) "ar" else "en"

                // Only proceed if the selected language is different from the current one
                if (selectedLanguage != currentLanguage) {
                    // Update the language using LocalUtil
                    LocalUtil.setLocal(requireActivity(), selectedLanguage)

                    // Recreate the activity to apply the language change
                    (activity as? MainActivity)?.recreate()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing when no selection is made
            }
        }
    }
}


