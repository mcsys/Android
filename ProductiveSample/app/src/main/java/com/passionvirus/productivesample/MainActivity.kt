package com.passionvirus.productivesample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    companion object {
        lateinit var mAcitivity : Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // LeakCanary
//        mAcitivity = this

        // Glide
        /*
        val imageView = findViewById<ImageView>(R.id.photo)
        Glide.with(this)
//            .load("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png")
            .load(R.drawable.leak_canary_icon)
            .into(imageView)
        */

        // Gson
        /*
        val gson = Gson()
        val user = User("mm@mm.com", "maemong")
        val json = gson.toJson(user)
        Log.d(TAG, json.toString())

        val str = gson.fromJson("\"hello\"", String::class.java)
        Log.d(TAG, "String $str")
        val newUser = gson.fromJson("{\"email\":\"mm@mm.com\",\"fullname\":\"mongmong\"}", User::class.java)
        Log.d(TAG, "${newUser.email} | ${newUser.fullname}")
        */

        // Okhttp
        val thread = Thread(Runnable {
            try {
                val get = OkGet()
                val resGet = get.run("https://raw.github.com/square/okhttp/master/README.md")
                Log.d(TAG, resGet)

                // Not work because permit
                val post = OkPost()
                val json = post.buildJson("paulo", "mamo")
                val resPost = post.post("http://roundsapp.com/post", json)
                Log.d(TAG, resPost)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })

        thread.start()
    }

    fun onButtonClicked() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
