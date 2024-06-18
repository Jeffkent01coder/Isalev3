package com.jeff.isalev3.ui.home.invoiceTabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jeff.isalev3.Repositories.DataRepository
import com.jeff.isalev3.Repositories.DataStoreRepository
import com.jeff.isalev3.ViewModels.AppViewModel
import com.jeff.isalev3.ViewModels.StateViewModelFactory
import com.jeff.isalev3.adapters.ProfomaAdapter
import com.jeff.isalev3.databinding.FragmentProfomaBinding


class ProfomaFragment : Fragment() {

    private var _binding: FragmentProfomaBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AppViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this, StateViewModelFactory(
                DataRepository(), DataStoreRepository.getInstance(requireContext())
            )
        )[AppViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfomaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCachedDetails()
        viewModel.savedPreferences.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getProfomas(it.bearerToken)
        }
        viewModel.getProfomasUIState.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            it.errorMessage?.let { error ->
                binding.fetchStateHolder.text = error
            }
            it.statusResponse?.let { status ->
                if (status == "true") {
                    if (it.observableData!!.profoma.isNullOrEmpty()) {
                        binding.fetchStateHolder.text = "No Profomas available"
                    } else {
                        val accountDetails = viewModel.savedPreferences.value
                        binding.profomaRecyclerview.apply {
                            adapter = ProfomaAdapter(it.observableData.profoma) { profomaDetail ->
                                //   InvoiceDetailBottomSheet(saleDetail).show(childFragmentManager,"Sales fragment")
                                Toast.makeText(requireContext(), "Works", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } else {
                    binding.fetchStateHolder.text = it.statusMessage
                }
            }
        }

    }


}