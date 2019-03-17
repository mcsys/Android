package com.passionvirus.cleanlist.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.passionvirus.cleanlist.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moveAbilityList()
    }

    private fun moveAbilityList() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, AbilityListFragment(), AbilityListFragment().TAG)
            .commit()
    }

    fun moveAbility(name : String, url : String) {
        val bundle = Bundle()
        bundle.putString("name", name)
        bundle.putString("url", url)

        val fragment = AbilityFragment()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, AbilityFragment(), AbilityFragment().TAG)
            .commit()
    }
}
