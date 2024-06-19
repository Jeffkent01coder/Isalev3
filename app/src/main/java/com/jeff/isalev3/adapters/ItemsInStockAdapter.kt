package com.jeff.isalev3.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.jeff.isalev3.R
import com.stanbestgroup.isalev2.Room.Entities

class ItemsInStockAdapter(private var onItemClicked: ((categoryIdplusSelection: String) -> Unit)? = null) :
    ListAdapter<Entities.ItemEntity, ItemsInStockAdapter.ItemVh>(StockComparator()) {
    class ItemVh(v: View) : RecyclerView.ViewHolder(v) {
        companion object {
            fun create(parent: ViewGroup): ItemVh {
                return ItemVh(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.pos_store_item, parent, false)
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVh {
        return ItemVh.create(parent)

    }

    override fun onBindViewHolder(holder: ItemVh, position: Int) {
        val item = getItem(position)
        holder.itemView.findViewById<MaterialTextView>(R.id.itemDescription).text = item.itemNm
        holder.itemView.findViewById<MaterialTextView>(R.id.itemUnitPrice).text =
            "Ksh." + item.dftPrc.toDouble().toString()
        holder.itemView.findViewById<MaterialTextView>(R.id.remainingStock).text =
            "Current stock - " + item.currentStock
        holder.itemView.findViewById<CheckBox>(R.id.item_selected_checkbox)
            .setOnCheckedChangeListener { buttonView, isChecked ->
                onItemClicked!!(item.itemNm + "~" + isChecked.toString())
            }
    }
}

class StockComparator : DiffUtil.ItemCallback<Entities.ItemEntity>() {
    override fun areItemsTheSame(
        oldItem: Entities.ItemEntity,
        newItem: Entities.ItemEntity
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Entities.ItemEntity,
        newItem: Entities.ItemEntity
    ): Boolean {
        return oldItem == newItem
    }

}
