package com.jeff.isalev3.ui.home.invoiceTabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jeff.isalev3.Repositories.DataRepository
import com.jeff.isalev3.Repositories.DataStoreRepository
import com.jeff.isalev3.ViewModels.AppViewModel
import com.jeff.isalev3.ViewModels.StateViewModelFactory
import com.jeff.isalev3.adapters.SalesAdapter
import com.jeff.isalev3.databinding.FragmentSalesBinding
import com.jeff.isalev3.ui.home.homeFragments.sheets.InvoiceDetailBottomSheet

class SalesFragment : Fragment() {

    private var _binding: FragmentSalesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AppViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSalesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this, StateViewModelFactory(
                DataRepository(), DataStoreRepository.getInstance(requireContext())
            )
        )[AppViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCachedDetails()
        viewModel.savedPreferences.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getSales(it.bearerToken)
        }
        viewModel.getSalesUIState.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            it.errorMessage?.let { error ->
                binding.fetchStateHolder.text = error
            }
            it.statusResponse?.let { status ->
                if (status == "true") {
                    if (it.observableData!!.sales.isNullOrEmpty()) {
                        binding.fetchStateHolder.apply {
                            text = "No sales made yet"
                            visibility = View.VISIBLE
                        }
                    } else {
                        val accountDetails = viewModel.savedPreferences.value
                        binding.salesRecyclerview.apply {
                            adapter = SalesAdapter(it.observableData.sales) { saleDetail ->
                                InvoiceDetailBottomSheet(saleDetail).show(
                                    childFragmentManager,
                                    "Sales fragment"
                                )
                            }
                        }
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