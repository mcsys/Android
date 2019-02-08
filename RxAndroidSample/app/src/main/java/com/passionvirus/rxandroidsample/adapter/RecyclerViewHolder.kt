package com.passionvirus.rxandroidsample.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.passionvirus.rxandroidsample.R
import com.passionvirus.rxandroidsample.ui.RecyclerViewItem
import io.reactivex.Observable

class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val item_image = itemView.findViewById<ImageView>(R.id.item_image)
    val item_title = itemView.findViewById<TextView>(R.id.item_title)

    fun getClickObserver(item : RecyclerViewItem) : Observable<RecyclerViewItem> {
        return Observable.create { e -> itemView.setOnClickListener {
            e.onNext(item)
        } }
    }
}