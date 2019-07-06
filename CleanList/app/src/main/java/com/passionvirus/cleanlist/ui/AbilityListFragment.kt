package com.passionvirus.cleanlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jakewharton.rxbinding3.view.clicks
import com.passionvirus.cleanlist.R
import com.passionvirus.cleanlist.adapter.AbilityListViewAdapter
import com.passionvirus.cleanlist.databinding.FragmentAbilitylistBinding
import com.passionvirus.cleanlist.viewmodel.AbilityListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit


class AbilityListFragment : Fragment() {
    val TAG = AbilityListFragment::class.java.simpleName

    private var compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val adapter = AbilityListViewAdapter()
        val binding: FragmentAbilitylistBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_abilitylist, container, false)
        binding.vm = AbilityListViewModel()
        binding.recyclerView.adapter = adapter

        val item = adapter.getItemPublishSubject()
            .throttleFirst(1000, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .subscribe{
                    s -> moveAbility(s.name, s.url)
            }
        compositeDisposable.add(item)

        val prev = binding.btnPrev.clicks()
            .throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe{
                binding.vm?.prevUrl?.let {
                    it.takeUnless { it.isEmpty() }
                        ?.run {
                            binding.vm?.getAbilityList(this)
                        }
                }
            }
        compositeDisposable.add(prev)

        val next = binding.btnNext.clicks()
            .throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe{
                binding.vm?.nextUrl?.let {
                    it.takeUnless { it.isEmpty() }
                        ?.run {
                            binding.vm?.getAbilityList(it) }
                }
            }
        compositeDisposable.add(next)

        return binding.root
    }

    private fun moveAbility(name : String, url : String) {
        (activity as MainActivity).moveAbility(name, url)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()

        compositeDisposable
            .takeUnless { it.isDisposed }
            ?.run { dispose() }
    }
}