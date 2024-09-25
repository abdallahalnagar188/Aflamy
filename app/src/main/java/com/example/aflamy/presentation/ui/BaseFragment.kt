package com.example.aflamy.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.aflamy.presentation.dialog.LoadingDialog
import com.example.domain.state.NetworkExtensionsActions


abstract class BaseFragment<out T : ViewBinding> : Fragment(),NetworkExtensionsActions {

    private var _binding: ViewBinding? = null

    @Suppress("UNCHECKED_CAST")
    protected open val binding: T
        get() = _binding as T


    protected abstract val bindingInflater: (LayoutInflater) -> ViewBinding
    private var isInitializeScreen = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = bindingInflater(inflater)
        return _binding!!.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    override fun onLoad(showLoading: Boolean) {
        super.onLoad(showLoading)
        LoadingDialog.showDialog()
        if (showLoading){ LoadingDialog.showDialog()}
        else {LoadingDialog.dismissDialog()}
    }

}