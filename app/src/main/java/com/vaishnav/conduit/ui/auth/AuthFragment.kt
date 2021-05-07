package com.vaishnav.conduit.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.tabs.TabLayout
import com.vaishnav.conduit.R
import com.vaishnav.conduit.databinding.FragmentAuthBinding

class AuthFragment : Fragment(){

    private var _binding : FragmentAuthBinding? = null
    private val binding get() = _binding!!
    private var navController : NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = _binding?.let { Navigation.findNavController(it.root.findViewById(R.id.auth_fragment_nav_host)) }

        binding.authTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> {
                        navController?.navigate(R.id.goToLoginFragment)
                    }
                    1 -> {
                        navController?.navigate(R.id.goToSignUpFragment)
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}