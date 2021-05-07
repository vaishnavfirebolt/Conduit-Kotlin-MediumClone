package com.vaishnav.conduit.ui.auth

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.vaishnav.conduit.databinding.FragmentLoginSignUpBinding

class LoginFragment : Fragment(){

    private var _binding : FragmentLoginSignUpBinding? = null
    private val binding get() = _binding!!
    private val authViewModel : AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginSignUpBinding.inflate(inflater, container, false)
        binding.usernameEditText.isVisible = false

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            submitButton.setOnClickListener {
                authViewModel.login(
                   emailEditText.text.toString(),
                   passwordEditText.text.toString()
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}