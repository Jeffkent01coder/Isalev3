package com.jeff.isalev3.ui.home.homeFragments

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
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
import com.jeff.isalev3.ui.home.homeFragments.sheets.LearnMoreBottomSheet
import com.stanbestgroup.isalev2.Room.RoomApplication

class HomeFragment : Fragment() {

    private lateinit var viewModel: AppViewModel

    private lateinit var binding: FragmentHomeBinding


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

        binding = FragmentHomeBinding.inflate(inflater, container, false)
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

                    /*"Branch: ${details?.branchName}-${details?.branchId}\n"
                            +"Physical address: ${details?.province}-${details?.street}\n"+"Managed by: ${details?.managerName}\n"
                          + "Phone number: ${details?.managerPhone}\n"+"Email: ${details?.managerEmail}"*/
                    .setMessage(
                        Html.fromHtml(
                            "<b>Branch: </b>${details.branchName}-${details.branchId}\n<br/>" +
                                    "<b>Physical address: </b>${details.province}-${details.street}\n<br/>" +
                                    "<b>Managed by: </b>${details.managerName}<br/>" +
                                    "<b>Phone number:</b> ${details.managerPhone}\n<br/>" +
                                    "<b>Email:</b> ${details.managerEmail}"
                        )
                    )
                    .setPositiveButton("Exit") { _, _ -> }

                    .show()
            }

        }
        viewModel.savedPreferences.observe(viewLifecycleOwner) {
            binding.totalSalesCash.text = "KES\n" + it.totalSales.toDouble().toString()
            binding.totalSales.text = "Sales\n" + it.numberOfSales
            binding.incurredVat.text =
                "Value added tax\n" + "KES " + it.totalTax.toDouble().toString()
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
                // e.g., navigate to ProfileFragment
                true
            }

            R.id.logout -> {
                // Handle Logout action
                // e.g., perform logout and navigate to login screen
                true
            }

            R.id.settingsFragment -> {
                // Handle Settings action
                navController.navigate(R.id.action_homeFragment_to_settingsFragment)
                true
            }

            R.id.reportsFragment -> {
                // Handle Reports action
                // e.g., navigate to ReportsFragment
                true
            }

            R.id.customerFragment -> {
                // Handle Customers action
                // e.g., navigate to CustomerFragment
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}
