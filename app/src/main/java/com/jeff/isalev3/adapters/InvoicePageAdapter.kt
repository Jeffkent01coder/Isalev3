package com.jeff.isalev3.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jeff.isalev3.ui.home.invoiceTabs.CreditNoteFragment
import com.jeff.isalev3.ui.home.invoiceTabs.ProfomaFragment
import com.jeff.isalev3.ui.home.invoiceTabs.SalesFragment

class InvoicePageAdapter(parentFragment: Fragment): FragmentStateAdapter(parentFragment) {
    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> SalesFragment()
            1-> ProfomaFragment()
            2-> CreditNoteFragment()
            else -> {
                throw IllegalArgumentException("Invalid tab position")
            }
        }
    }
}