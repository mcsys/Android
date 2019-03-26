package com.passionvirus.cleanlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.passionvirus.cleanlist.R
import com.passionvirus.cleanlist.api.Ability
import com.passionvirus.cleanlist.databinding.FragmentAbilityBinding
import com.passionvirus.cleanlist.viewmodel.AbilityViewModel


class AbilityFragment : Fragment() {
    val TAG = AbilityFragment::class.java.simpleName

    private lateinit var name : String
    private lateinit var url : String
    lateinit var binding : FragmentAbilityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = arguments?.getParcelable<Ability>("ability")?.name ?: ""
        url = arguments?.getParcelable<Ability>("ability")?.url ?: ""
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ability, container, false)
        binding.vm = AbilityViewModel(name, url)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

}