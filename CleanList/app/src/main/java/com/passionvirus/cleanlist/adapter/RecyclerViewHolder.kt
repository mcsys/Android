package com.passionvirus.cleanlist.adapter

import androidx.recyclerview.widget.RecyclerView
import com.passionvirus.cleanlist.BR
import com.passionvirus.cleanlist.databinding.MainItemBinding

class RecyclerViewHolder(val binding: MainItemBinding) : RecyclerView.ViewHolder(binding.root) {


    fun bind(item : RecyclerViewItem) {
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