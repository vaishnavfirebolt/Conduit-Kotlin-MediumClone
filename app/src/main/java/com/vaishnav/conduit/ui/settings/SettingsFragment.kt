package com.vaishnav.conduit.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.vaishnav.conduit.databinding.FragmentSettingsBinding
import com.vaishnav.conduit.ui.auth.AuthViewModel

class SettingsFragment : Fragment() {

    private var _binding : FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private  val authViewModel by activityViewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authViewModel.user.observe({lifecycle}){
            binding.apply {
                imageEditText.setText(it?.image ?: "")
                usernameEditText.setText(it?.username ?: "")
                bioEditText.setText(it?.bio ?: "")
                emailEditText.setText(it?.email ?: "")
            }
        }
        binding.apply {
            submitButton.setOnClickListener {
                authViewModel.update(
                    bio = bioEditText.text.toString(),
                    username = usernameEditText.text.toString().takeIf { it.isNotBlank() },
                    image = imageEditText.text.toString(),
                    email = emailEditText.text.toString().takeIf { it.isNotBlank() },
                    password = passwordEditText.text.toString().takeIf { it.isNotBlank() },
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}