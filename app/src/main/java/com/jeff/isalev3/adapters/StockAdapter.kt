package com.jeff.isalev3.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.jeff.isalev3.R
import com.stanbestgroup.isalev2.Fragments.store.data.Item
import java.text.DecimalFormat

class StockAdapter(
    val context: Context,
    val type: String,
    val stocks: List<Item>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<StockAdapter.ViewHolder>() {
    val decimalFormat = DecimalFormat("#,##0.00")

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.txtViewStockProductName)
        val txtPrice: TextView = itemView.findViewById(R.id.txtViewStockProductPrice)
        val txtQty: TextView = itemView.findViewById(R.id.txtViewStockProductQty)
        val imgView: ImageView = itemView.findViewById(R.id.imgViewStockEdit)
        val mV: MaterialCardView = itemView.findViewById(R.id.mvViewStock)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.view_stock_recyclerview_model, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = stocks[position]
        holder.txtName.text = item.itemNm
        holder.txtPrice.text = "Price: ${decimalFormat.format(item.dftPrc.toDouble())}"
        holder.txtQty.text = "Current Stock: ${decimalFormat.format(item.currentStock.toDouble())}"
        holder.mV.setOnClickListener {
            onClickListener.onClick(item)
        }
        if (type == "adjust") {
            holder.imgView.visibility = View.VISIBLE
        } else {
            holder.imgView.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return stocks.size
    }

    class OnClickListener(val clickListener: (item: Item) -> Unit) {
        fun onClick(item: Item) = clickListener(item)
    }
}