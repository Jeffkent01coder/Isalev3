package com.jeff.isalev3.ui.home.homeFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.jeff.isalev3.R
import com.jeff.isalev3.databinding.FragmentStoreBinding

class StoreFragment : Fragment() {
    private lateinit var binding: FragmentStoreBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.currentStock.setOnClickListener {
            it.findNavController().navigate(R.id.action_storeFragment_to_currentStockFragment)
        }
        binding.addNewStock.setOnClickListener {
            it.findNavController().navigate(R.id.action_storeFragment_to_addNewStockFragment)
        }
        binding.adjustStock.setOnClickListener {
            it.findNavController().navigate(R.id.action_storeFragment_to_adjustStockFragment)
        }
        binding.managePurchases.setOnClickListener {
            it.findNavController().navigate(R.id.action_storeFragment_to_managePurchasesFragment)
        }
        binding.manageImports.setOnClickListener {
            it.findNavController().navigate(R.id.action_storeFragment_to_manageImportsFragment)
        }
        binding.stockMovement.setOnClickListener {
            it.findNavController().navigate(R.id.action_storeFragment_to_stockMovementFragment)
        }
        binding.stockComposition.setOnClickListener {
            it.findNavController().navigate(R.id.action_storeFragment_to_stockCompositionFragment)
        }
    }



}