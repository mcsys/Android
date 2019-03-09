package com.passionvirus.cleanlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.passionvirus.cleanlist.databinding.AbilitylistItemBinding

class AbilityListViewAdapter : RecyclerView.Adapter<AbilityListViewHolder>() {
    private val mItems = ArrayList<AbilityListViewItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityListViewHolder {
        val binding = AbilitylistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AbilityListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: AbilityListViewHolder, position: Int) {
        val item = mItems[position]
        holder.bind(item)
    }

    fun updateItems(items: ArrayList<AbilityListViewItem>) {
        mItems.addAll(items)
    }
}