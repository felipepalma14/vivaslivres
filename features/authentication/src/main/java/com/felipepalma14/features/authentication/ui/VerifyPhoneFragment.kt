package com.felipepalma14.features.authentication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.felipepalma14.features.authentication.R
import com.felipepalma14.features.authentication.databinding.FragmentLoginBinding
import com.felipepalma14.features.authentication.databinding.FragmentVerifyPhoneBinding


class VerifyPhoneFragment : Fragment() {
    private lateinit var binding: FragmentVerifyPhoneBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentVerifyPhoneBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}