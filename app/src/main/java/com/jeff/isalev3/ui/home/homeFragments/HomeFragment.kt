package com.jeff.isalev3.ui.home.homeFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jeff.isalev3.R
import com.jeff.isalev3.databinding.FragmentHomeBinding
import com.jeff.isalev3.ui.home.homeFragments.sheets.LearnMoreBottomSheet

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.learMore.setOnClickListener {
            LearnMoreBottomSheet().show(childFragmentManager, "Home page")
        }
    }

    //    @Deprecated("Deprecated in Java")
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.home_menu, menu)
//        // Set icon tint for each item programmatically
//        for (i in 0 until menu.size()) {
//            val menuItem = menu.getItem(i)
//            val drawable = menuItem.icon
//            drawable?.setColorFilter(
//                ContextCompat.getColor(requireContext(), R.color.white),
//                PorterDuff.Mode.SRC_IN
//            )
//        }
//        super.onCreateOptionsMenu(menu, inflater)
//    }
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
