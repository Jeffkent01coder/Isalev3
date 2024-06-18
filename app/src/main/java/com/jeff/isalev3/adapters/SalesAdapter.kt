package com.jeff.isalev3.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.jeff.isalev3.R
import com.jeff.isalev3.models.SaleDetail
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class SalesAdapter(
    private val salesList: List<SaleDetail>?,
    private var onItemClicked: ((invoicedata: SaleDetail) -> Unit)? = null
) : RecyclerView.Adapter<SalesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalesViewHolder {
        return SalesViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return salesList!!.size
    }

    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: SalesViewHolder, position: Int) {
        val item = salesList!![position]
        var formattedDate: String?
        // Format the LocalDateTime to the desired format
        try {
            val inputFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")

            // Parse the string to LocalDateTime
            val dateTime = LocalDateTime.parse(item.cfmDt, inputFormatter)

            // Define the output format
            val outputFormatter = DateTimeFormatter.ofPattern("dd.MMMM.yyyy hh:mm a")
            formattedDate = dateTime.format(outputFormatter)

        } catch (e: Exception) {
            formattedDate = "Error parsing date"
        }


        holder.itemView.findViewById<MaterialButton>(R.id.view_invoice).isEnabled =
            item.items.isNotEmpty()

        holder.itemView.findViewById<MaterialTextView>(R.id.receipt_no).text =
            "Receipt number. " + item.invcNo.toString()
        holder.itemView.findViewById<MaterialTextView>(R.id.date_of_sale).text = formattedDate
        holder.itemView.findViewById<MaterialTextView>(R.id.sales_amount).text =
            "Ksh." + item.totAmt.toString()
        holder.itemView.findViewById<MaterialButton>(R.id.view_invoice).setOnClickListener {
            onItemClicked!!(item)
        }
        holder.itemView.findViewById<MaterialButton>(R.id.credit_note).setOnClickListener {

        }

    }

    fun convertToFormattedDate(input: String): String {
        val inputFormat = SimpleDateFormat("yyyyMMddHHmmss", Locale.US)
        val outputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US)

        val date = inputFormat.parse(input)
        return outputFormat.format(date)
    }
}

class SalesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    companion object {
        fun create(parent: ViewGroup): SalesViewHolder {
            return SalesViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.sales_item_layout, parent, false)
            )
        }
    }

}