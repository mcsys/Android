package com.passionvirus.cleanlist.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("item")
    fun bindingItem(recyclerView: RecyclerView, items: List<RecyclerViewItem>?) {
        val adapter = recyclerView.adapter as RecyclerViewAdapter

        items?.let {
            /*
            adapter.updateItems(items)
            adapter.notifyDataSetChanged()
            */
        }
    }
}