package com.passionvirus.cleanlist.adapter

import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object BindingAdapters {
    @JvmStatic @BindingAdapter("abilityListItem")
    fun bindingAbilityListItem(recyclerView: RecyclerView, items: ArrayList<AbilityListViewItem>) {
        val adapter = recyclerView.adapter as AbilityListViewAdapter

        items.let {
            if (it.size > 0) {
                adapter.updateItems(items)
//                adapter.notifyDataSetChanged()
            }
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