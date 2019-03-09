package com.passionvirus.cleanlist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.passionvirus.cleanlist.BR
import com.passionvirus.cleanlist.databinding.AbilitylistItemBinding

class AbilityListViewHolder(private val binding: AbilitylistItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item : AbilityListViewItem) {
        binding.setVariable(BR.item, item)
    }

    /*
    fun getClickObserver(item : RecyclerViewItem) : Observable<RecyclerViewItem> {
        return Observable.create { e -> itemView.setOnClickListener {
            e.onNext(item)
        } }
    }
    */
}