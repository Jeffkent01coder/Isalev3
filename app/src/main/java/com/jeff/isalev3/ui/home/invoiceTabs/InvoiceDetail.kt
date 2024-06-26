package com.jeff.isalev3.ui.home.invoiceTabs

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.os.CancellationSignal
import android.os.ParcelFileDescriptor
import android.print.PageRange
import android.print.PrintAttributes
import android.print.PrintDocumentAdapter
import android.print.PrintDocumentInfo
import android.print.PrintManager
import android.provider.ContactsContract
import android.telephony.SmsManager
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jeff.isalev3.Repositories.DataRepository
import com.jeff.isalev3.Repositories.DataStoreRepository
import com.jeff.isalev3.ViewModels.AppViewModel
import com.jeff.isalev3.ViewModels.StateViewModelFactory
import com.jeff.isalev3.databinding.FragmentInvoiceDetailBinding
import com.stanbestgroup.isalev2.Room.RoomApplication
import java.io.FileOutputStream

private const val REQUEST_CONTACT_PERMISSION = 100
private const val REQUEST_CONTACT_PICKER = 101

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class InvoiceDetail : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private var invoiceType: String? = null
    private var invoiceDetails: String? = null
    private lateinit var viewModel: AppViewModel
    private var _binding: FragmentInvoiceDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("InvoiceDetail", "onCreate")
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
        Log.d("InvoiceDetail", "onCreateView")
        _binding = FragmentInvoiceDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("InvoiceDetail", "onViewCreated")
        Log.d("account details", viewModel.savedPreferences.value?.bearerToken.toString())

        if (invoiceType == "sale") {
            binding.textPreview.text = Html.fromHtml(invoiceDetails)
        }

        binding.smsInvoiceDetails.setOnClickListener {
            Log.d("InvoiceDetail", "SMS button clicked")
            Toast.makeText(requireActivity(), "Sms me", Toast.LENGTH_SHORT).show()
            checkPermissionsAndPickContact()
        }

        binding.printInvoiceDetails.setOnClickListener {
            Log.d("InvoiceDetail", "Print button clicked")
            Toast.makeText(requireActivity(), "Print me", Toast.LENGTH_SHORT).show()
            printInvoice()
        }

        binding.shareInvoiceDetails.setOnClickListener {
            Log.d("InvoiceDetail", "Share button clicked")
            Toast.makeText(requireActivity(), "Invoice me", Toast.LENGTH_SHORT).show()
            shareInvoice()
        }
    }

    private fun checkPermissionsAndPickContact() {
        Log.d("InvoiceDetail", "checkPermissionsAndPickContact")
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_CONTACTS),
                REQUEST_CONTACT_PERMISSION
            )
        } else {
            pickContact()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d("InvoiceDetail", "onRequestPermissionsResult")
        if (requestCode == REQUEST_CONTACT_PERMISSION && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pickContact()
        }
    }

    private fun pickContact() {
        Log.d("InvoiceDetail", "pickContact")
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        startActivityForResult(intent, REQUEST_CONTACT_PICKER)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("InvoiceDetail", "onActivityResult")
        if (requestCode == REQUEST_CONTACT_PICKER && resultCode == Activity.RESULT_OK && data != null) {
            val contactUri: Uri = data.data ?: return
            val cursor: Cursor? =
                requireContext().contentResolver.query(contactUri, null, null, null, null)
            cursor?.use {
                if (it.moveToFirst()) {
                    val contactId: String =
                        it.getString(it.getColumnIndex(ContactsContract.Contacts._ID))
                    val hasPhoneNumber: Int =
                        it.getInt(it.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                    if (hasPhoneNumber > 0) {
                        val phones: Cursor? = requireContext().contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} = ?",
                            arrayOf(contactId),
                            null
                        )
                        phones?.use { phoneCursor ->
                            if (phoneCursor.moveToFirst()) {
                                val phoneNumber: String = phoneCursor.getString(
                                    phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                                )
                                Log.d("InvoiceDetail", "Selected phone number: $phoneNumber")
                                sendInvoiceViaSms(phoneNumber)
                            }
                        }
                    }
                }
            }
        } else {
            Log.d("InvoiceDetail", "Contact picker cancelled or no data returned")
        }
    }

    private fun sendInvoiceViaSms(phoneNumber: String) {
        try {
            Log.d("InvoiceDetail", "Sending SMS to $phoneNumber")
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, invoiceDetails, null, null)
            Log.d("SMS", "SMS sent successfully")
        } catch (e: Exception) {
            Log.e("SMS", "Failed to send SMS", e)
        }
    }

    private fun printInvoice() {
        Log.d("InvoiceDetail", "printInvoice")
        val printManager =
            ContextCompat.getSystemService(requireContext(), PrintManager::class.java)
        val printAdapter = object : PrintDocumentAdapter() {
            override fun onLayout(
                oldAttributes: PrintAttributes?,
                newAttributes: PrintAttributes?,
                cancellationSignal: CancellationSignal?,
                callback: LayoutResultCallback?,
                extras: Bundle?
            ) {
                if (cancellationSignal?.isCanceled == true) {
                    callback?.onLayoutCancelled()
                    return
                }

                val info = PrintDocumentInfo.Builder("invoice.pdf")
                    .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                    .build()
                callback?.onLayoutFinished(info, true)
            }

            override fun onWrite(
                pages: Array<PageRange>?,
                destination: ParcelFileDescriptor?,
                cancellationSignal: CancellationSignal?,
                callback: WriteResultCallback?
            ) {
                try {
                    destination?.fileDescriptor?.let { fileDescriptor ->
                        val outputStream = FileOutputStream(fileDescriptor)
                        outputStream.write(invoiceDetails?.toByteArray())
                        outputStream.close()
                        callback?.onWriteFinished(arrayOf(PageRange.ALL_PAGES))
                    }
                } catch (e: Exception) {
                    callback?.onWriteFailed(e.toString())
                }
            }
        }
        printManager?.print("invoice", printAdapter, PrintAttributes.Builder().build())
    }

    private fun shareInvoice() {
        Log.d("InvoiceDetail", "shareInvoice")
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, invoiceDetails)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share invoice via"))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
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

