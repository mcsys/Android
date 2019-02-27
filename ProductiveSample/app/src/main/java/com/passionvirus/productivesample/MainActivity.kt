package com.passionvirus.productivesample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var mAcitivity : Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // LeakCanary
//        mAcitivity = this

        val imageView = findViewById<ImageView>(R.id.photo)
        Glide.with(this)
//            .load("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png")
            .load(R.drawable.leak_canary_icon)
            .into(imageView)

    }

    fun onButtonClicked() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
