package com.jeff.isalev3.ui.home.stock.additems

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jeff.isalev3.R
import com.jeff.isalev3.Repositories.DataRepository
import com.jeff.isalev3.Repositories.DataStoreRepository
import com.jeff.isalev3.ViewModels.AppViewModel
import com.jeff.isalev3.ViewModels.StateViewModelFactory
import com.jeff.isalev3.databinding.FragmentAddNewStockBinding
import com.jeff.isalev3.ui.home.stock.additems.model.AddItemData
import com.jeff.isalev3.ui.models.CategoryPos
import com.jeff.isalev3.ui.models.Code
import com.jeff.isalev3.ui.models.ItemClassification
import com.jeff.isalev3.ui.models.Tax
import com.stanbestgroup.isalev2.Room.RoomApplication
import java.math.BigDecimal
import java.math.RoundingMode

class AddNewStockFragment : Fragment() {
    private var _binding: FragmentAddNewStockBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AppViewModel
    private lateinit var successDialog: AlertDialog

    private var taxCode: String = ""
    private var countryCode: String = ""
    private var classificationCode: String = ""
    private var productCode: String = ""
    private var packagingCode: String = ""
    private var quantityCode: String = ""
    private var itemCategoryId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddNewStockBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(
            this, StateViewModelFactory(
                DataRepository(), DataStoreRepository.getInstance(requireContext()), (requireActivity().application as RoomApplication).repository
            )
        )[AppViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configure drop-down selections
        setupDropdowns()

        // Add button click listener
        binding.addButton.setOnClickListener {
            submitItem()
        }

        // Observe UI state for add item response
        viewModel.addItemUIState.observe(viewLifecycleOwner) { uiState ->
            binding.progressBar.isVisible = false
            uiState.errorMessage?.let { error ->
                binding.statusMessage.isVisible = true
                binding.statusMessage.text = error
            } ?: run {
                binding.statusMessage.isVisible = false
            }
            uiState.successMessage?.let {
                showTransactionSuccess()
            }
        }
    }

    private fun setupDropdowns() {
        // Setup dropdown behavior for AutoCompleteTextView fields
        binding.autoCountry.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val selectedObject = parent.getItemAtPosition(position) as Code
            countryCode = selectedObject.cd
        }
        binding.imgAutoCountry.setOnClickListener { binding.autoCountry.showDropDown() }

        binding.autoClassification.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val selectedObject = parent.getItemAtPosition(position) as ItemClassification
            classificationCode = selectedObject.itemClsCd
        }
        binding.imgAutoClassification.setOnClickListener { binding.autoClassification.showDropDown() }

        binding.autoProductType.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val selectedObject = parent.getItemAtPosition(position) as Code
            productCode = selectedObject.cd
        }
        binding.imgAutoProductType.setOnClickListener { binding.autoProductType.showDropDown() }

        binding.autoPackaging.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val selectedObject = parent.getItemAtPosition(position) as Code
            packagingCode = selectedObject.cd
        }
        binding.imgAutoPackaging.setOnClickListener { binding.autoPackaging.showDropDown() }

        binding.autoTax.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val selectedObject = parent.getItemAtPosition(position) as Tax
            taxCode = selectedObject.cd
        }
        binding.imgAutoTax.setOnClickListener { binding.autoTax.showDropDown() }

        binding.autoQuantity.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val selectedObject = parent.getItemAtPosition(position) as Code
            quantityCode = selectedObject.cd
        }
        binding.imgAutoQuantity.setOnClickListener { binding.autoQuantity.showDropDown() }

        binding.autoCategory.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val selectedObject = parent.getItemAtPosition(position) as CategoryPos
            itemCategoryId = selectedObject.id
        }
        binding.imgAutoCategory.setOnClickListener { binding.autoCategory.showDropDown() }
    }

    private fun submitItem() {
        val addItemData = AddItemData(
            addInfo = binding.edtAddItemName.text.toString(),
            bcd = binding.edtAddItemBarcode.text.toString(),
            btchNo = binding.edtAddItemBatch.text.toString(),
            currentStock = binding.edtAddItemStock.text.toString(),
            dftPrc = binding.edtAddItemPrice.text.toString().toDoubleOrNull() ?: 0.0,
            grpPrcL1 = 0.0,
            grpPrcL2 = 0.0,
            grpPrcL3 = 0.0,
            grpPrcL4 = 0.0,
            isrcAplcbYn = if (binding.switchAddItemInsurance.isChecked) "Y" else "N",
            itemCategoryId = itemCategoryId.toString(),
            itemCd = productCode,
            itemCdDf = "",
            itemClsCd = classificationCode,
            itemNm = binding.edtAddItemName.text.toString(),
            itemStdNm = "",
            itemTyCd = productCode,
            modrId = "modrId",
            modrNm = "modrNm",
            orgnNatCd = countryCode,
            pkgUnitCd = packagingCode,
            qtyUnitCd = quantityCode,
            regrId = "regrId",
            regrNm = "regrNm",
            taxTyCd = taxCode,
            useYn = "Y"
        )

        viewModel.savedPreferences.value?.let {
            binding.progressBar.isVisible = true
            viewModel.addItems(it.bearerToken, addItemData)
        }
    }

    private fun showTransactionSuccess() {
        val successDialogView = layoutInflater.inflate(R.layout.general_add_item_success_dialog, null)
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(successDialogView)
        val successDialog: AlertDialog = builder.create()
        successDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        successDialog.setOnDismissListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        successDialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
