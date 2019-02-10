package com.passionvirus.rxandroidsample.adapter

import androidx.recyclerview.widget.RecyclerView
import com.passionvirus.rxandroidsample.BR
import com.passionvirus.rxandroidsample.databinding.RecyclerviewItemBinding
import io.reactivex.Observable


class RecyclerViewHolder(val binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item : RecyclerViewItem) {
        binding.setVariable(BR.item, item)
    }

    fun getClickObserver(item : RecyclerViewItem) : Observable<RecyclerViewItem> {
        return Observable.create { e -> itemView.setOnClickListener {
            e.onNext(item)
        } }
    }
}

// Not Use DataBinding
/*
class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val item_image = itemView.findViewById<ImageView>(R.id.item_image)
    val item_title = itemView.findViewById<TextView>(R.id.item_title)

    fun getClickObserver(item : RecyclerViewItem) : Observable<RecyclerViewItem> {
        return Observable.create { e -> itemView.setOnClickListener {
            e.onNext(item)
        } }
    }
}
*/