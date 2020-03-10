package com.passionvirus.cleanlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.passionvirus.cleanlist.databinding.AbilitylistItemBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class AbilityListViewAdapter : RecyclerView.Adapter<AbilityListViewHolder>() {
    private val items = ArrayList<AbilityListViewItem>()
    private var publishSubject = PublishSubject.create<AbilityListViewItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilityListViewHolder {
        val binding = AbilitylistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AbilityListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AbilityListViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.getClickObserver(item)
            .subscribe(publishSubject)
    }

    fun updateItems(newItem: ArrayList<AbilityListViewItem>) {
        newItem.takeIf { it.size > 0 }
            .run {
                if (items != this)
                items.clear()
            }
        items.addAll(newItem)
    }

    fun getItemPublishSubject() : PublishSubject<AbilityListViewItem> {
        return publishSubject
    }
}