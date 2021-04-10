package com.felipepalma14.vivaselivres.ui.login.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.felipepalma14.data.model.Country
import com.felipepalma14.vivaselivres.R
import com.felipepalma14.vivaselivres.databinding.FragmentLoginBinding
import com.felipepalma14.vivaselivres.ui.custom.CustomProgressView
import com.felipepalma14.vivaselivres.ui.login.presentation.LoginViewModel
import com.felipepalma14.vivaselivres.utilities.*
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var country: Country? = null
    private var progressView: CustomProgressView? = null

    private val viewModel by activityViewModels<LoginViewModel>()

    private lateinit var binding: FragmentLoginBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        subscribeObservers()
    }

    private fun setupView() {
        binding.viewmodel = viewModel
        progressView = CustomProgressView(requireContext())
        setDefaultCountry()

        binding.btnGetOtp.setOnClickListener {
            validate()
        }
        /*
         binding.btnGetOtp.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_verify)
        }
         */
    }

    private fun validate() {
        try {
            Utils.closeKeyBoard(requireActivity())
            val mobileNo = viewModel.mobile.value?.trim()
            val country = viewModel.country.value
            when {
                Validator.isMobileNumberEmpty(mobileNo) -> snack(requireActivity(), "Digite um número válido")
                //country == null -> snack(requireActivity(), "Select a country")
                !Validator.isPhoneNumberValid(country!!.code, mobileNo!!) -> snack(
                    requireActivity(),
                    "Digite um número válido"
                )
                Utils.isNoInternet(requireContext()) -> snackNet(requireActivity())
                else -> {
                    viewModel.sendOtpToPhone(country,mobileNo,requireActivity())
                    viewModel.setProgress(true)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setDefaultCountry() {
        try {
            country = Utils.getDefaultCountry()
            viewModel.setCountry(country!!)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun subscribeObservers() {
        try {
            viewModel.getProgress().observe(viewLifecycleOwner, {
                progressView?.toggle(it)
            })

            viewModel.getVerificationId().observe(viewLifecycleOwner, { vCode ->
                vCode?.let {
                    viewModel.setProgress(false)
                    //viewModel.resetTimer()
                    //viewModel.setVCodeNull()
                    //viewModel.setEmptyText()
                    if (findNavController().isValidDestination(R.id.destination_fragment_login))
                        findNavController().navigate(R.id.action_login_to_verify)
                }
            })


            /*
        viewModel.getFailed().observe(viewLifecycleOwner, {
            progressView?.dismiss()
        })

        viewModel.getTaskResult().observe(viewLifecycleOwner, { taskId ->
            if (taskId!=null && viewModel.getCredential().value?.smsCode.isNullOrEmpty())
                viewModel.fetchUser(taskId)
        })

        viewModel.userProfileGot.observe(viewLifecycleOwner, { success ->
            if (success && viewModel.getCredential().value?.smsCode.isNullOrEmpty()
                && findNavController().isValidDestination(R.id.FLogIn)) {
                requireActivity().toastLong("Authenticated successfully using Instant verification")
                findNavController().navigate(R.id.action_FLogIn_to_FProfile)
            }
        })
        */
        } catch (e: Exception) {
            e.printStackTrace()
            Timber.e(e)
        }
    }


    override fun onDestroy() {
        try {
            progressView?.dismissIfShowing()
            super.onDestroy()
        } catch (e: Exception) {
            e.printStackTrace()
            Timber.e(e)
        }
    }
}