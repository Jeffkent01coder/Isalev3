package com.jeff.isalev3.ui.moreMenu.settings

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jeff.isalev3.R
import com.jeff.isalev3.databinding.FragmentSettingsBinding
import com.jeff.isalev3.ui.auth.ResetPassword

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        binding.changePassword.setOnClickListener {
            val intent = Intent(requireActivity(), ResetPassword::class.java)
            startActivity(intent)
        }
    }


}