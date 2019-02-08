package com.passionvirus.rxandroidsample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.passionvirus.rxandroidsample.R
import com.passionvirus.rxandroidsample.ui.RecyclerViewItem
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.recyclerview_item.view.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewHolder>() {

    private val mItems = ArrayList<RecyclerViewItem>()
    private var mPublishSubject = PublishSubject.create<RecyclerViewItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = mItems[position]
        holder.itemView.item_image.setImageDrawable(item.image)
        holder.itemView.item_title.text = item.title
        holder.getClickObserver(item).subscribe(mPublishSubject)
    }

    fun updateItems(items : List<RecyclerViewItem>) {
        mItems.addAll(items)
    }

    fun updateItems(item : RecyclerViewItem) {
        mItems.add(item)
    }

    fun getItemPublishSubject() : PublishSubject<RecyclerViewItem> {
        return mPublishSubject
    }

    // Move to RecyclerViewHolder Class
    /*
    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val mImage = itemView.findViewById<ImageView>(R.id.item_image)
        private val mTitle = itemView.findViewById<TextView>(R.id.item_title)

        private fun getClickObserver(item : RecyclerViewItem) : Observable<RecyclerViewItem> {
            return Observable.create { e -> itemView.setOnClickListener {
                e.onNext(item)
            } }
        }
    }
    */
}