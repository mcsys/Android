package com.passionvirus.rxandroidsample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.passionvirus.rxandroidsample.R
import com.passionvirus.rxandroidsample.databinding.ActivityLoopBinding
import com.passionvirus.rxandroidsample.viewmodel.LoopViewModel

class LoopActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_loop)
        binding.viewModel = ViewModelProviders.of(this).get(LoopViewModel::class.java)
    }
}