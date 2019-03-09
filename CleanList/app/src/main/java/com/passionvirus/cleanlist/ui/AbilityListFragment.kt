package com.passionvirus.cleanlist.ui

import android.os.Bundle
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
    lateinit var binding : FragmentAbilitylistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_abilitylist, container, false)
        binding.recyclerView.adapter = AbilityListViewAdapter()
        binding.vm = AbilityListViewModel()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

}