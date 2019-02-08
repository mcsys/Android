package com.passionvirus.rxandroidsample.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.passionvirus.rxandroidsample.R
import com.passionvirus.rxandroidsample.adapter.RecyclerViewAdapter
import com.passionvirus.rxandroidsample.databinding.ActivityRecyclerviewBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRecyclerviewBinding
    private val adapter = RecyclerViewAdapter()
    private val layoutManager = LinearLayoutManager(baseContext)

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recyclerview)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

        adapter.getItemPublishSubject()
            .subscribe{ s -> toast(s.title)}
    }

    @SuppressLint("CheckResult")
    override fun onStart() {
        super.onStart()

        getItemObservable()
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe{item ->
                adapter.updateItems(item)
                adapter.notifyDataSetChanged()
            }
    }

    private fun getItemObservable() : Observable<RecyclerViewItem>? {
        val pm = application.packageManager
        val intent = Intent(Intent.ACTION_MAIN, null)
            .addCategory(Intent.CATEGORY_LAUNCHER)

        return Observable.fromIterable(pm.queryIntentActivities(intent, 0))
            .sorted(ResolveInfo.DisplayNameComparator(pm))
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map {
                val image = it.activityInfo.loadIcon(pm)
                val title = it.activityInfo.loadLabel(pm).toString()
                return@map RecyclerViewItem(image, title)
            }

        /*
        return Observable.fromIterable(pm.queryIntentActivities(intent, 0))
            .sorted(ResolveInfo.DisplayNameComparator(pm))
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .map { item -> {
                val image = item.activityInfo.loadIcon(pm)
                val title = item.activityInfo.loadLabel(pm).toString()
                RecyclerViewItem(image, title)
            }
            }
        */

    }

    private fun toast(s : String) {
        Toast.makeText(baseContext, s, Toast.LENGTH_SHORT).show()
    }

}