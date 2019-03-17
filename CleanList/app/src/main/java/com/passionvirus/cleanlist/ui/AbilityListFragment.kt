package com.passionvirus.cleanlist.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.passionvirus.cleanlist.R
import com.passionvirus.cleanlist.adapter.AbilityListViewAdapter
import com.passionvirus.cleanlist.databinding.FragmentAbilitylistBinding
import com.passionvirus.cleanlist.viewmodel.AbilityListViewModel




class AbilityListFragment : Fragment() {
    val TAG = AbilityListFragment::class.java.simpleName

    lateinit var binding : FragmentAbilitylistBinding
    val adapter = AbilityListViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_abilitylist, container, false)
        binding.vm = AbilityListViewModel()
        binding.recyclerView.adapter = adapter
        adapter.getItemPublishSubject().subscribe{
                s -> moveAbility(s.name, s.url)
        }

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
    }
}