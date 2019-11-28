package com.passionvirus.productivesample

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.passionvirus.productivesample.databinding.ActivityNavigationBinding

class NavigationActivity: FragmentActivity() {
    private lateinit var binding: ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_navigation)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_container) as NavHostFragment? ?: return

        binding.navView.setupWithNavController(host.navController)
        binding.navView.setOnNavigationItemSelectedListener {
            if (it.itemId == R.id.home_list) {
//                Log.d("TEST1234", "ID: ${findNavController(R.id.nav_container).currentDestination.toString()}")
//                Log.d("TEST1234", "ID: ${findNavController(R.id.nav_container).navigateUp()}")
                findNavController(R.id.nav_container).navigate(R.id.two)

                true
            }
            false
        }

    }
}