package com.passionvirus.cleanlist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.passionvirus.cleanlist.R
import com.passionvirus.cleanlist.adapter.RecyclerViewAdapter
import com.passionvirus.cleanlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.recyclerView.adapter = RecyclerViewAdapter()
    }
}
