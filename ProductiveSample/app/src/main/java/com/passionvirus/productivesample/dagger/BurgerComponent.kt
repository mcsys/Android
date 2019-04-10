package com.passionvirus.productivesample.dagger

import com.passionvirus.productivesample.DaggerActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [BurgerModule::class])
interface BurgerComponent {

    fun inject(activity: DaggerActivity)
}