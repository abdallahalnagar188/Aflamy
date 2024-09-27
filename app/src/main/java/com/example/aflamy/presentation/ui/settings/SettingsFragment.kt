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
        val languages = resources.getStringArray(R.array.language_options)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, languages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.languageSpinner.adapter = adapter

        val currentLanguage = LocalUtil.getLang() ?: "en"
        binding.languageSpinner.setSelection(if (currentLanguage == "ar") 1 else 0)

        binding.languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedLanguage = if (position == 1) "ar" else "en"

                if (selectedLanguage != currentLanguage) {
                    LocalUtil.setLocal(requireActivity(), selectedLanguage)
                    (activity as? MainActivity)?.recreate()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

}


