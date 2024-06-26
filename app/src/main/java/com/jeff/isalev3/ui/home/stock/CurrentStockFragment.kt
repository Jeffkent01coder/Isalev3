//package com.jeff.isalev3.ui.home.stock
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.jeff.isalev3.Repositories.DataRepository
//import com.jeff.isalev3.Repositories.DataStoreRepository
//import com.jeff.isalev3.ViewModels.AppViewModel
//import com.jeff.isalev3.ViewModels.StateViewModelFactory
//import com.jeff.isalev3.adapters.StockAdapter
//import com.jeff.isalev3.databinding.FragmentCurrentStockBinding
//import com.stanbestgroup.isalev2.Room.RoomApplication
//
//class CurrentStockFragment : Fragment() {
//
//    private var _binding: FragmentCurrentStockBinding? = null
//    private val binding get() = _binding!!
//    private lateinit var viewModel: AppViewModel
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        viewModel = ViewModelProvider(
//            this, StateViewModelFactory(
//                DataRepository(),
//                DataStoreRepository.getInstance(requireContext()),
//                (requireActivity().application as RoomApplication).repository
//            )
//        )[AppViewModel::class.java]
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentCurrentStockBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        binding.recyclerViewStock.layoutManager = LinearLayoutManager(requireContext())
//
//        viewModel.getCachedDetails()
//        viewModel.savedPreferences.observe(viewLifecycleOwner) {
//            binding.progressBar.visibility = View.VISIBLE
//            viewModel.getStock(it.bearerToken)
//        }
//        viewModel.getStockDataUIState.observe(viewLifecycleOwner) {
//            binding.progressBar.visibility = View.GONE
//            it.errorMessage?.let { error ->
//                binding.fetchStateHolder.text = error
//            }
//            it.statusResponse?.let { status ->
//                if (status == "true") {
//                    if (it.observableData!!.items.isNullOrEmpty()) {
//                        binding.fetchStateHolder.apply {
//                            text = "No Items in Stock"
//                            visibility = View.VISIBLE
//                        }
//                    } else {
//                        val accountDetails = viewModel.savedPreferences.value
//                        binding.recyclerViewStock.apply {
//                            adapter = StockAdapter(it.observableData.items)
//                        }
//                    }
//                } else {
//                    binding.fetchStateHolder.apply {
//                        text = it.statusMessage
//                        visibility = View.VISIBLE
//                    }
//                }
//            }
//        }
//    }
//
//
//}

package com.jeff.isalev3.ui.home.stock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jeff.isalev3.Repositories.DataRepository
import com.jeff.isalev3.Repositories.DataStoreRepository
import com.jeff.isalev3.ViewModels.AppViewModel
import com.jeff.isalev3.ViewModels.StateViewModelFactory
import com.jeff.isalev3.adapters.StockAdapter
import com.jeff.isalev3.databinding.FragmentCurrentStockBinding
import com.jeff.isalev3.models.StockItem
import com.stanbestgroup.isalev2.Room.RoomApplication

class CurrentStockFragment : Fragment() {

    private var _binding: FragmentCurrentStockBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AppViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this, StateViewModelFactory(
                DataRepository(),
                DataStoreRepository.getInstance(requireContext()),
                (requireActivity().application as RoomApplication).repository
            )
        )[AppViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCurrentStockBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewStock.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getCachedDetails()
        viewModel.savedPreferences.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getStock(it.bearerToken)
        }
        viewModel.getStockDataUIState.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            it.errorMessage?.let { error ->
                binding.fetchStateHolder.text = error
            }
            it.statusResponse?.let { status ->
                if (status == "true") {
                    if (it.observableData!!.items.isNullOrEmpty()) {
                        binding.fetchStateHolder.apply {
                            text = "No Items in Stock"
                            visibility = View.VISIBLE
                        }
                    } else {
                        binding.fetchStateHolder.visibility = View.GONE
                        binding.recyclerViewStock.adapter = StockAdapter(
                            requireContext(),
                            "default",
                            it.observableData.items,
                            StockAdapter.OnClickListener { item ->
                                // Handle item click
                                Toast.makeText(requireActivity(), "My Stock", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                } else {
                    binding.fetchStateHolder.apply {
                        text = it.statusMessage
                        visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}

