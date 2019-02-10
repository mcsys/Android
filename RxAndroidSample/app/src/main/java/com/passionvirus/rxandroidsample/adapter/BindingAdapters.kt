package com.passionvirus.rxandroidsample.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("item")
    fun bindingItem(recyclerView: RecyclerView, item: List<RecyclerViewItem>?) {
        val adapter = recyclerView.adapter as RecyclerViewAdapter

        item?.let {
            adapter.updateItems(item)
            adapter.notifyDataSetChanged()
        }
    }

    // Not Use DataBinding
    /*
    @JvmStatic
    @BindingAdapter("item")
    fun bindingItem(recyclerView: RecyclerView, item: RecyclerViewItem?) {
        val adapter = recyclerView.adapter as RecyclerViewAdapter

        item?.let {
            adapter.updateItems(item)
            adapter.notifyDataSetChanged()
        }
    }
    */
}