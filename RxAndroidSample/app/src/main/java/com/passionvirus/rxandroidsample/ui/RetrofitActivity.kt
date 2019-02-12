package com.passionvirus.rxandroidsample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.passionvirus.rxandroidsample.R
import com.passionvirus.rxandroidsample.adapter.RestfulAdapter
import com.passionvirus.rxandroidsample.data.Contributor
import com.passionvirus.rxandroidsample.databinding.ActivityRetrofitBinding
import io.reactivex.Observable

class RetrofitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRetrofitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_retrofit)

    }

    // TODO - 풀로 구현한다.
    /*
    private fun startRx() {
        val service = RestfulAdapter.getServiceApi()
        val observable = service.getObContributors(sName, sRepo)

    }
    */
}