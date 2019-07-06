package com.passionvirus.cleanlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.passionvirus.cleanlist.databinding.AbilitylistItemBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class AbilityListViewAdapter : RecyclerView.Adapter<AbilityListViewHolder>() {
    private val mItems = ArrayList<AbilityListViewItem>()
    private var mPublishSubject = PublishSubject.create<AbilityListViewItem>()

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
        holder.getClickObserver(item)
            .subscribe(mPublishSubject)
    }

    fun updateItems(items: ArrayList<AbilityListViewItem>) {
        mItems.takeIf { it.size > 0 }
            .run { mItems.clear() }
        mItems.addAll(items)
    }

    fun getItemPublishSubject() : PublishSubject<AbilityListViewItem> {
        return mPublishSubject
    }
}