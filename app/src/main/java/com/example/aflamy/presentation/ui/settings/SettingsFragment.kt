package com.example.aflamy.presentation.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.example.aflamy.databinding.FragmentSettingsBinding
import com.example.aflamy.presentation.ui.BindingFragment


class SettingsFragment : BindingFragment<FragmentSettingsBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentSettingsBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}