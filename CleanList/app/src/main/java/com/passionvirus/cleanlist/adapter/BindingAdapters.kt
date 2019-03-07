package com.passionvirus.cleanlist.adapter

import android.view.View
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

    @JvmStatic @BindingAdapter("viewVisible")
    fun viewVisible(view: View, status: Boolean) {
        view.visibility = if(status) {
            View.VISIBLE
        }
        else {
            View.INVISIBLE
        }
        view.isEnabled = status
    }

    @JvmStatic @BindingAdapter("viewEnabled")
    fun viewEnabled(view: View, status: Boolean) {
        view.isEnabled = status
    }
}