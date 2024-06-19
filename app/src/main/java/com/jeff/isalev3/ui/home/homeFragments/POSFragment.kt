package com.jeff.isalev3.ui.home.homeFragments

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.jeff.isalev3.R
import com.jeff.isalev3.Repositories.DataRepository
import com.jeff.isalev3.Repositories.DataStoreRepository
import com.jeff.isalev3.ViewModels.AppViewModel
import com.jeff.isalev3.ViewModels.StateViewModelFactory
import com.jeff.isalev3.adapters.ItemsInStockAdapter
import com.jeff.isalev3.databinding.FragmentPOSBinding
import com.stanbestgroup.isalev2.Room.Entities
import com.stanbestgroup.isalev2.Room.RoomApplication

class POSFragment : Fragment() {

    private var stockList:List<Entities.ItemEntity>?=null
    private var _binding: FragmentPOSBinding? =null
    private val binding get()  = _binding!!
    private lateinit var viewModel: AppViewModel
    val selectedItems = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, StateViewModelFactory(
            DataRepository(), DataStoreRepository.getInstance(requireContext()),(requireActivity().application as RoomApplication).repository)
        )[AppViewModel::class.java]
        arguments?.let {

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding =  FragmentPOSBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val stockAdapter = ItemsInStockAdapter(){
            //By default only selected items will be added to the list in the format (itemName~checkboxSelection)
            //When an object is added or removed the common identifier will be the itemName
            if(selectedItems.contains(it.split("~")[0]+"~"+"true")){
                val currentSelection = it.split("~")[1]
                if(currentSelection=="false"){
                    selectedItems.remove(it.split("~")[0]+"~"+"true")
                }
            }else{
                selectedItems.add(it)
            }
            viewModel.updateSelectedItems(selectedItems)

        }
        viewModel.cartCountUIState.observe(viewLifecycleOwner){
            if(it>0){
                binding.checkout.apply {
                    visibility = View.VISIBLE
                    text = "Checkout ($it)"
                }
            }
            else {
                binding.checkout.apply {
                    visibility = View.GONE

                }
            }
        }
        viewModel.itemsInStore.observe(viewLifecycleOwner){
            it?.let { sls->
                stockList =sls.filter { itemEntity ->itemEntity.status !="deleted"  }
                stockAdapter.submitList(stockList)
            }
        }
        viewModel.itemCategories.observe(viewLifecycleOwner){
            it?.let {stockList->
                if(stockList.isNotEmpty()){
                    binding.categoriesChipGroup.removeAllViews()
                    binding.filterLayout.visibility = View.VISIBLE
                    stockList.forEach { category ->
                        val c = Chip(requireContext())
                        c.apply {
                            id = category.categoryId
                            isCheckable = true
                            isCheckedIconVisible=true
                            text = category.category.lowercase()
                            chipStrokeWidth =0.0f
                            checkedIcon = AppCompatResources.getDrawable(requireContext(),R.drawable.twotone_filter_alt_24)
                            chipBackgroundColor = ColorStateList.valueOf(resources.getColor(R.color.md_theme_primary,null))
                            checkedIconTint =ColorStateList.valueOf(resources.getColor(R.color.white,null))
                            setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
                        }
                        binding.categoriesChipGroup.addView(c)
                    }
                }
            }

        }
        binding.itemsInStockRecyclerview.apply {
            adapter = stockAdapter
        }

        binding.categoriesChipGroup.setOnCheckedStateChangeListener { _, checkedIds ->

            if(checkedIds.isNotEmpty()){
                stockAdapter.submitList(stockList?.filter { itemEntity ->itemEntity.itemCategoryId ==checkedIds[0]  })
            } else {
                //restore default list
                stockAdapter.submitList(stockList)
            }
            // Log.d("Checked-id is",checkedIds[0].toString())
        }
    }

    override fun onDestroyView() {
        //Resets the cart
        selectedItems.clear()
        viewModel.updateSelectedItems(selectedItems)
        binding.categoriesChipGroup.removeAllViews()
        viewModel.itemCategories.removeObservers(viewLifecycleOwner)

        super.onDestroyView()
    }


}