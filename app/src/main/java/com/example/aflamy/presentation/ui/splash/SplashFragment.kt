package com.example.aflamy.presentation.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.aflamy.R
import com.example.aflamy.databinding.FragmentSpalshBinding
import com.example.aflamy.presentation.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSpalshBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentSpalshBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Using Handler with Looper to post a delayed action
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }, 2000)
    }
}
