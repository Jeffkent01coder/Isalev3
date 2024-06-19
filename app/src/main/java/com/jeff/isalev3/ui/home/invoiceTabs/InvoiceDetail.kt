package com.jeff.isalev3.ui.home.invoiceTabs

import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.jeff.isalev3.R
import com.jeff.isalev3.Repositories.DataRepository
import com.jeff.isalev3.Repositories.DataStoreRepository
import com.jeff.isalev3.ViewModels.AppViewModel
import com.jeff.isalev3.ViewModels.StateViewModelFactory
import com.jeff.isalev3.databinding.FragmentInvoiceDetailBinding
import com.stanbestgroup.isalev2.Room.RoomApplication

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InvoiceDetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class InvoiceDetail : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var invoiceType: String? = null
    private var invoiceDetails: String? = null
    private lateinit var viewModel: AppViewModel
    private var _binding: FragmentInvoiceDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(
            this, StateViewModelFactory(
                DataRepository(),
                DataStoreRepository.getInstance(requireContext()),
                (requireActivity().application as RoomApplication).repository
            )
        )[AppViewModel::class.java]
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            invoiceType = it.getString("invoice_type")
            invoiceDetails = it.getString("invoice_details")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInvoiceDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.getCachedDetails()
        Log.d("account details", viewModel.savedPreferences.value?.bearerToken.toString())
        if (invoiceType == "sale") {
            binding.textPreview.text = Html.fromHtml(invoiceDetails)

        } else if (invoiceType == "profoma") {
        } else {
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InvoiceDetail.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InvoiceDetail().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}