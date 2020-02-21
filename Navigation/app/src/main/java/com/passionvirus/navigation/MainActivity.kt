package com.passionvirus.navigation

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity(),
    StartFragment.OnFragmentInteractionListener,
    StepOneFragment.OnFragmentInteractionListener,
    StepTwoFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var isFirst = true
        val test = findViewById<Button>(R.id.navigate_action_button)

        test.setOnClickListener {
            Log.d("TEST1234", "buttonTest Click")
            try {
                if (isFirst)
                    findNavController(R.id.my_nav_host_fragment).navigate(R.id.action_startFragment_to_stepOneFragment)
                else
                    findNavController(R.id.my_nav_host_fragment).navigate(R.id.action_startFragment_to_stepTwoFragment)
            }
            catch (e: Exception) {
                isFirst = !isFirst
                findNavController(R.id.my_nav_host_fragment).popBackStack()
            }
        }

    }

    override fun onFragmentInteraction(uri: Uri) {
        Log.d("MainActivity","Uri: $uri")
    }
}
