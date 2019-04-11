package com.passionvirus.productivesample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.passionvirus.productivesample.dagger.BeefPatty
import com.passionvirus.productivesample.dagger.Burger
import com.passionvirus.productivesample.dagger.BurgerModule
import com.passionvirus.productivesample.dagger.DaggerBurgerComponent
import javax.inject.Inject


class DaggerActivity : AppCompatActivity() {

    @Inject
    lateinit var burger: Burger

    @Inject
    lateinit var patty: BeefPatty

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val component = DaggerBurgerComponent.builder()
            .burgerModule(BurgerModule())
            .build()

        component.inject(this)

        Log.d("Dagger", "bun : ${burger.bun.bun}")
        Log.d("Dagger", "patty : ${patty.patty}")
    }
}