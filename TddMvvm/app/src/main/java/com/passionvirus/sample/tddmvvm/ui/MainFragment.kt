package com.passionvirus.sample.tddmvvm.ui


import androidx.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.passionvirus.sample.tddmvvm.R
import com.passionvirus.sample.tddmvvm.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    val TAG = MainFragment::class.java.simpleName

    lateinit var binding : FragmentMainBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

        return binding.root
    }
}