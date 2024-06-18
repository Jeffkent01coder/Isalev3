package com.jeff.isalev3.ui.moreMenu.reports

import android.app.DatePickerDialog
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.jeff.isalev3.R
import com.jeff.isalev3.databinding.FragmentReportsBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import java.util.Calendar
import java.util.UUID

class ReportsFragment : Fragment() {

    private lateinit var binding: FragmentReportsBinding

    private lateinit var salesSummaryReports: View
    private lateinit var zReports: View
    private lateinit var closeIcon: ImageView
    private lateinit var dailyXReport: View
    private lateinit var websiteLink: TextView
    private lateinit var salesSummaryReportsDialog: AlertDialog
    private lateinit var zReportsDialog: AlertDialog
    private lateinit var dailyXReportDialog: AlertDialog

    var printXArray = arrayListOf<String>()
    var printer: String = "18:10:77:00:10:46" //"0F:02:17:60:70:30"  //"18:10:77:00:10:46"
    var printFamily: String = "B"

    lateinit var bluetoothAdapter: BluetoothAdapter;
    lateinit var bluetoothSocket: BluetoothSocket;
    lateinit var bluetoothDevice: BluetoothDevice;

    val PERMISSION_REQUEST_CODE = 100;

    var MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb")
    val TAG = "Bluetooth Socket"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReportsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeResources()
    }
    private fun initializeResources() {
        salesSummaryReports = binding.salesSummaryReports
        zReports = binding.zReports
        dailyXReport = binding.dailyXReport
//        binding.closeIcon.setOnClickListener {
//            findNavController().navigateUp()
//        }
        websiteLink = binding.websiteLink
        websiteLink.paintFlags = websiteLink.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        zReports.setOnClickListener {
            zReportsDialog.show()
        }
        dailyXReport.setOnClickListener {
            dailyXReportDialog.show()
        }
        salesSummaryReports.setOnClickListener {
            salesSummaryReportsDialog.show()
        }

        val salesSummaryReportsDialogView =
            layoutInflater.inflate(R.layout.sales_summary_report_dialog_layout, null)
        val salesSummarySubmitButton =
            salesSummaryReportsDialogView.findViewById<MaterialButton>(R.id.enter_button)
        val spnMode = salesSummaryReportsDialogView.findViewById<Spinner>(R.id.spnPeriodicalType)
        val dateFrom =
            salesSummaryReportsDialogView.findViewById<TextInputEditText>(R.id.edtPeriodicalDateFrom)
        val dateTo =
            salesSummaryReportsDialogView.findViewById<TextInputEditText>(R.id.edtPeriodicalDateTo)
        val lnDate = salesSummaryReportsDialogView.findViewById<LinearLayout>(R.id.lnPeriodicalDate)
        val lnSeq =
            salesSummaryReportsDialogView.findViewById<LinearLayout>(R.id.lnPeriodicalSequence)
        val seqFrom =
            salesSummaryReportsDialogView.findViewById<TextInputEditText>(R.id.edtPeriodicalSeqFrom)
        val seqTo =
            salesSummaryReportsDialogView.findViewById<TextInputEditText>(R.id.edtPeriodicalSeqTo)

        var typeMode = "date"
        spnMode.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    lnDate.visibility = View.VISIBLE
                    lnSeq.visibility = View.GONE
                    typeMode = "date"
                } else {
                    lnDate.visibility = View.GONE
                    lnSeq.visibility = View.VISIBLE
                    typeMode = "sequence"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Handle the case when nothing is selected (optional)
            }
        }

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireActivity(),
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                // Handle the selected date (e.g., update the EditText)
                val formattedDate =
                    String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay)
                dateFrom.setText(formattedDate)
            },
            year,
            month,
            day
        )
        dateFrom.setOnClickListener { datePickerDialog.show() }

        val datePickerDialogTo = DatePickerDialog(
            requireActivity(),
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                // Handle the selected date (e.g., update the EditText)
                val formattedDate =
                    String.format("%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay)
                dateTo.setText(formattedDate)
            },
            year,
            month,
            day
        )
        dateTo.setOnClickListener { datePickerDialogTo.show() }


        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(salesSummaryReportsDialogView)
        salesSummaryReportsDialog = builder.create()
        salesSummaryReportsDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        salesSummarySubmitButton.setOnClickListener {
            GlobalScope.launch {
                postFilterZReport(
                    typeMode,
                    datefrom = dateFrom.text.toString(),
                    dateto = dateTo.text.toString(),
                    seqfrom = seqFrom.text.toString(),
                    seqto = seqTo.text.toString()
                )
            }
            salesSummaryReportsDialog.dismiss()
        }

        val zReportDialogView =
            layoutInflater.inflate(R.layout.z_report_dialog_layout, null)
        val zReportSubmitButton =
            zReportDialogView.findViewById<MaterialButton>(R.id.enter_button)
        val zBuilder = AlertDialog.Builder(requireActivity())
        zBuilder.setView(zReportDialogView)
        zReportsDialog = zBuilder.create()
        zReportsDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        zReportSubmitButton.setOnClickListener {
            GlobalScope.launch { postGetZReport() }
            zReportsDialog.dismiss()
        }

        val dailyXReportDialogView =
            layoutInflater.inflate(R.layout.daily_x_report_dialog, null)
        val dailyXReportSubmitButton =
            dailyXReportDialogView.findViewById<MaterialButton>(R.id.enter_button)
        val dailyXReportBuilder = AlertDialog.Builder(requireActivity())
        dailyXReportBuilder.setView(dailyXReportDialogView)
        dailyXReportDialog = dailyXReportBuilder.create()
        dailyXReportDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dailyXReportSubmitButton.setOnClickListener {
//            GlobalScope.launch { postGetXReport() }
            dailyXReportDialog.dismiss()
        }
    }

    private suspend fun postFilterZReport(
        type: String,
        datefrom: String,
        dateto: String,
        seqfrom: String,
        seqto: String
    ) {
        try {
            val jsonComp = JSONObject()
            jsonComp.put("type", type)
            jsonComp.put("datefrom", datefrom)
            jsonComp.put("dateto", dateto)
            jsonComp.put("seqfrom", seqfrom)
            jsonComp.put("seqto", seqto)

            val requestBod = jsonComp.toString()
            val requestBody = RequestBody.create(MediaType.parse("application/json"), requestBod)

//            Log.e("Json filter Body", "=" + requestBod)
//            val bToken =
//                "Bearer " + PoSPreference.getStringPrefs(requireActivity(), "BEARERTOKEN", "")
//                    .toString()
//            val responseBody = ApiServer.apiService.postFilterreport(bToken, requestBody)
//            val responseJson = JSONObject(responseBody.string())
//            Log.e("Post Filter Zreport", "=" + responseJson.toString())
//            if (responseJson.getBoolean("status")) {
//                formatFilterZreportArray(responseJson)
//            } else {
//                Log.e("Post Customer", "=" + responseJson.getString("message"))
//            }
        } catch (e: Exception) {
            // Handle errors
        }
    }

    private suspend fun postGetZReport() {
//        try {
//            val bToken =
//                "Bearer " + PoSPreference.getStringPrefs(requireActivity(), "BEARERTOKEN", "")
//                    .toString()
//            val responseBody = ApiServer.apiService.postGetZreport(bToken)
//            val responseJson = JSONObject(responseBody.string())
//            Log.e("Post Zreport", "=" + responseJson.toString())
//            if (responseJson.getBoolean("status")) {
//                formatZreportArray(responseJson)
//            } else {
//                Log.e("Post Customer", "=" + responseJson.getString("message"))
//            }
//        } catch (e: Exception) {
//            // Handle errors
//        }
    }

}