package com.jeff.isalev3.ui.home.homeFragments.sheets

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textview.MaterialTextView
import com.jeff.isalev3.R
import com.jeff.isalev3.Repositories.DataRepository
import com.jeff.isalev3.Repositories.DataStoreRepository
import com.jeff.isalev3.ViewModels.AppViewModel
import com.jeff.isalev3.ViewModels.StateViewModelFactory
import com.jeff.isalev3.adapters.SoldItemsAdapter
import com.jeff.isalev3.models.SaleDetail
import com.stanbestgroup.isalev2.Room.RoomApplication
import java.text.SimpleDateFormat
import java.util.Locale

class InvoiceDetailBottomSheet(private val saleDetail: SaleDetail) : BottomSheetDialogFragment() {
    private var _rootView: View? = null
    private val rootView get() = _rootView!!
    private lateinit var viewModel: AppViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(
            this, StateViewModelFactory(
                DataRepository(),
                DataStoreRepository.getInstance(requireContext()),
                (requireActivity().application as RoomApplication).repository
            )
        )[AppViewModel::class.java]
        _rootView = inflater.inflate(R.layout.fragment_invoice_detail, container)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCachedDetails()
        val businessDetails = viewModel.savedPreferences.value!!
        rootView.findViewById<MaterialTextView>(R.id.business_name).text = "${businessDetails.name}"
        rootView.findViewById<MaterialTextView>(R.id.business_tel).text =
            Html.fromHtml("<b>TEL:</b> ${businessDetails.email}")
        rootView.findViewById<MaterialTextView>(R.id.business_mail).text =
            Html.fromHtml("<b>EMAIL:</b> ${businessDetails.phone}")
        rootView.findViewById<MaterialTextView>(R.id.business_pin).text =
            Html.fromHtml("<b>PIN:</b> ${businessDetails.tin}")
        rootView.findViewById<MaterialTextView>(R.id.client_name).text =
            Html.fromHtml("<b>CLIENT PIN:</b> ${saleDetail.custNm}")
        rootView.findViewById<MaterialTextView>(R.id.client_pin).text =
            Html.fromHtml("<b>CLIENT:</b> ${saleDetail.custTin}")
        rootView.findViewById<MaterialTextView>(R.id.sub_total).text =
            "${saleDetail.totTaxblAmt.toDouble()}"
        rootView.findViewById<MaterialTextView>(R.id.VAT).text =
            "${saleDetail.totTaxAmt.toDouble()}"

        rootView.findViewById<MaterialTextView>(R.id.total).text = "${saleDetail.totAmt.toDouble()}"

        //A-EX
        rootView.findViewById<MaterialTextView>(R.id.aexamount).text =
            "${saleDetail.taxblAmtA.toDouble()}"
        rootView.findViewById<MaterialTextView>(R.id.aextaxedamount).text =
            "${saleDetail.taxAmtA.toDouble()}"
        //B-16
        rootView.findViewById<MaterialTextView>(R.id.b16amount).text =
            "${saleDetail.taxblAmtB.toDouble()}"
        rootView.findViewById<MaterialTextView>(R.id.b16taxedamount).text =
            "${saleDetail.taxAmtB.toDouble()}"
        //C-0
        rootView.findViewById<MaterialTextView>(R.id.camount).text =
            "${saleDetail.taxblAmtC.toDouble()}"
        rootView.findViewById<MaterialTextView>(R.id.ctaxedamount).text =
            "${saleDetail.taxAmtC.toDouble()}"
        //D-0
        rootView.findViewById<MaterialTextView>(R.id.damount).text =
            "${saleDetail.taxblAmtD.toDouble()}"
        rootView.findViewById<MaterialTextView>(R.id.dtaxedamount).text =
            "${saleDetail.taxAmtD.toDouble()}"
        //E-8
        rootView.findViewById<MaterialTextView>(R.id.e8amount).text =
            "${saleDetail.taxblAmtE.toDouble()}"
        rootView.findViewById<MaterialTextView>(R.id.e8taxedamount).text =
            "${saleDetail.taxAmtE.toDouble()}"

        //SCU Information
        rootView.findViewById<MaterialTextView>(R.id.scuDate).text =
            Html.fromHtml("<b>Date:</b> ${convertToFormattedDate(saleDetail.cfmDt)}")
        rootView.findViewById<MaterialTextView>(R.id.scuInvoiceNo).text =
            Html.fromHtml("<b>Invoice number:</b> ${businessDetails.scu}/${saleDetail.invcNo}")
        rootView.findViewById<MaterialTextView>(R.id.scuInternaldata).text =
            Html.fromHtml("<b>Internal data:</b> ${saleDetail.intrlData}")
        rootView.findViewById<MaterialTextView>(R.id.recieptSignature).text =
            Html.fromHtml("<b>Receipt signature:</b> ${saleDetail.rcptSign}")

        rootView.findViewById<RecyclerView>(R.id.items_sold_recyclerview).apply {
            adapter = SoldItemsAdapter(saleDetail.items)
        }
        saleDetail.short_url?.let {
            viewModel.generateQR(it, androidx.appcompat.R.color.primary_dark_material_dark)
        }
        viewModel.qrCode.observe(this.viewLifecycleOwner) {
            rootView.findViewById<ImageView>(R.id.recieptQR).setImageBitmap(it)
        }


    }

    fun convertToFormattedDate(input: String): String {
        val inputFormat = SimpleDateFormat("yyyyMMddHHmmss", Locale.US)
        val outputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US)
        val date = inputFormat.parse(input)
        return outputFormat.format(date)
    }

}