package com.passionvirus.cleanlist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.passionvirus.cleanlist.R
import com.passionvirus.cleanlist.api.Ability


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
        // Todo
        /*
        val abi = Ability(name, url)
        val bundle = Bundle()
        bundle.putParcelable(Ability.name, abi)
//        bundle.putString("name", name)
//        bundle.putString("url", url)

        val fragment = AbilityFragment()
        fragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment, AbilityFragment().TAG)
            .commit()
        */
    }
}
