package com.passionvirus.productivesample

import android.app.Activity
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName

    private val receiver = CustomReceiver()
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
        /*
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
        */

        // Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(RestService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RestService::class.java)
        val call = service.getUser("mcsys")
        call.enqueue(object: Callback<GithubUser> {
            override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                if(response.isSuccessful) {
                    val user = response.body()
                    Log.d(TAG, "login: ${user!!.login}")
                }
                Log.d(TAG, response.toString())
            }

            override fun onFailure(call: Call<GithubUser>, t: Throwable) {
                Log.d(TAG, "onFailure")
            }
        })
    }

    private fun registerReceiver() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_BATTERY_LOW)
        intentFilter.addAction(CUSTOM_ACTION)
        registerReceiver(receiver, intentFilter)
    }

    private fun unregisterReceiver() {
        unregisterReceiver(receiver)
    }

    fun onButtonClicked(v: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun onButtonClicked2(v: View) {
        val intent = Intent(CUSTOM_ACTION)
        sendBroadcast(intent)
    }


    override fun onResume() {
        super.onResume()
        registerReceiver()
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver()
    }


}
