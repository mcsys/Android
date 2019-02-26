package com.passionvirus.productivesample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var mAcitivity : Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAcitivity = this

    }

    fun onButtonClicked() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
