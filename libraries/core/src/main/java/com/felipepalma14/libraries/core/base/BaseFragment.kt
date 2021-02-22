package com.felipepalma14.libraries.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

internal abstract class BaseFragment<VM : ViewModel, VDB : ViewDataBinding> : Fragment() {

    protected val viewModel: VM by lazy { ViewModelProvider(this).get(getViewModelClass()) }

    protected lateinit var binding: VDB

    protected abstract fun getViewModelClass(): Class<VM>

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, getLayoutId(), container, false)
        return binding.root
    }
}