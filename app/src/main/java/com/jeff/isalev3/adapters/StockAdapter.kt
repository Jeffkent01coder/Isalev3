//package com.jeff.isalev3.adapters
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.jeff.isalev3.databinding.ViewStockRecyclerviewModelBinding
//import com.jeff.isalev3.models.StockItem
//import java.text.DecimalFormat
//
//class StockAdapter(private val stockList: List<StockItem>) :
//    RecyclerView.Adapter<StockAdapter.ViewHolder>() {
//
//    private val decimalFormat = DecimalFormat("#,##0.00")
//
//    inner class ViewHolder(val binding: ViewStockRecyclerviewModelBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(item: StockItem) {
//            binding.txtViewStockProductName.text = item.itemNm
//            binding.txtViewStockProductPrice.text = "Price: ${decimalFormat.format(item.dftPrc)}"
//            binding.txtViewStockProductQty.text = "Current Stock: ${decimalFormat.format(item.currentStock)}"
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val binding = ViewStockRecyclerviewModelBinding.inflate(
//            LayoutInflater.from(parent.context),
//            parent,
//            false
//        )
//        return ViewHolder(binding)
//    }
//
//    override fun getItemCount(): Int = stockList.size
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val obj = stockList[position]
//        holder.bind(obj)
//    }
//}

package com.jeff.isalev3.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.jeff.isalev3.R
import com.jeff.isalev3.databinding.ViewStockRecyclerviewModelBinding
import com.jeff.isalev3.models.StockItem
import java.text.DecimalFormat

class StockAdapter(
    val context: Context,
    val type: String,
    private val stockList: List<StockItem>,
    val clickListener: OnClickListener
) : RecyclerView.Adapter<StockAdapter.ViewHolder>() {

    private val decimalFormat = DecimalFormat("#,##0.00")

    inner class ViewHolder(val binding: ViewStockRecyclerviewModelBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: StockItem) {
            binding.txtViewStockProductName.text = item.itemNm
            binding.txtViewStockProductPrice.text = "Price: ${decimalFormat.format(item.dftPrc)}"
            binding.txtViewStockProductQty.text =
                "Current Stock: ${decimalFormat.format(item.currentStock)}"

            // Setting visibility of imgView based on the type
            if (type == "adjust") {
                binding.imgViewStockEdit.visibility = View.VISIBLE
            } else {
                binding.imgViewStockEdit.visibility = View.GONE
            }

            // Click listener for the MaterialCardView
            binding.mvViewStock.setOnClickListener {
                clickListener.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewStockRecyclerviewModelBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = stockList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val obj = stockList[position]
        holder.bind(obj)
    }

    class OnClickListener(val clickListener: (item: StockItem) -> Unit) {
        fun onClick(item: StockItem) = clickListener(item)
    }
}
