package com.jeff.isalev3.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.jeff.isalev3.R
import com.jeff.isalev3.models.ItemDetail

class SoldItemsAdapter(private val items: List<ItemDetail>) :
    RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.create(parent)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val itemSold = items[position]
        holder.itemView.findViewById<MaterialTextView>(R.id.item_name).text = itemSold.itemNm
        holder.itemView.findViewById<MaterialTextView>(R.id.item_quantity).text = itemSold.qty
        holder.itemView.findViewById<MaterialTextView>(R.id.item_unit_price).text = itemSold.prc
        holder.itemView.findViewById<MaterialTextView>(R.id.item_tax_category).text =
            itemSold.taxTyCd

    }
}

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    companion object {
        fun create(parent: ViewGroup): ItemViewHolder {
            return ItemViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.sales_item, parent, false)
            )
        }
    }
}
