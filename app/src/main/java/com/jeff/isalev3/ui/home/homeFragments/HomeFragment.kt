package com.jeff.isalev3.ui.home.homeFragments

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jeff.isalev3.R
import com.jeff.isalev3.Repositories.DataRepository
import com.jeff.isalev3.Repositories.DataStoreRepository
import com.jeff.isalev3.ViewModels.AppViewModel
import com.jeff.isalev3.ViewModels.StateViewModelFactory
import com.jeff.isalev3.databinding.FragmentHomeBinding
import com.jeff.isalev3.ui.auth.Login
import com.jeff.isalev3.ui.home.homeFragments.sheets.LearnMoreBottomSheet
import com.stanbestgroup.isalev2.Room.RoomApplication

class HomeFragment : Fragment() {

    private lateinit var viewModel: AppViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(
            this, StateViewModelFactory(
                DataRepository(),
                DataStoreRepository.getInstance(requireContext()),
                (requireActivity().application as RoomApplication).repository
            )
        )[AppViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCachedDetails()
        val details = viewModel.savedPreferences.value

        binding.learMore.setOnClickListener {
            LearnMoreBottomSheet().show(childFragmentManager, "Home page")
        }

        binding.showDetails.setOnClickListener {
            details?.let {
                MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogtext)
                    .setIcon(R.drawable.baseline_verified_24)
                    .setTitle(Html.fromHtml("<b>${details.name}</b>"))
                    .setMessage(
                        Html.fromHtml(
                            "<b>Branch: </b>${details.branchName}-${details.branchId}<br/>" +
                                    "<b>Physical address: </b>${details.province}-${details.street}<br/>" +
                                    "<b>Managed by: </b>${details.managerName}<br/>" +
                                    "<b>Phone number: </b>${details.managerPhone}<br/>" +
                                    "<b>Email: </b>${details.managerEmail}"
                        )
                    )
                    .setPositiveButton("Exit", null)
                    .show()
            }
        }

        viewModel.savedPreferences.observe(viewLifecycleOwner) {
            it?.let {
                binding.totalSalesCash.text = "KES\n${it.totalSales.toDouble()}"
                binding.totalSales.text = "Sales\n${it.numberOfSales}"
                binding.incurredVat.text = "Value added tax\nKES ${it.totalTax.toDouble()}"
            }
        }
    }
    
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController()
        return when (item.itemId) {
            R.id.Profile -> {
                // Handle Profile action
                true
            }
            R.id.logout -> {
                // Handle Logout action
                val intent  = Intent(requireActivity(), Login::class.java)
                startActivity(intent)
                Toast.makeText(requireActivity(), "Log out", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.settingsFragment -> {
                navController.navigate(HomeFragmentDirections.actionHomeFragmentToSettingsFragment())
                Toast.makeText(requireActivity(), "To settings", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.reportsFragment -> {
                navController.navigate(HomeFragmentDirections.actionHomeFragmentToReportsFragment())
                true
            }
            R.id.customerFragment -> {
                navController.navigate(HomeFragmentDirections.actionHomeFragmentToCustomerFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
