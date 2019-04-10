package com.passionvirus.productivesample.dagger

import dagger.Module
import dagger.Provides


@Module
class BurgerModule {

    @Provides
    internal fun provideBurger(bun: WheatBun, patty: BeefPatty): Burger {
        return Burger(bun, patty)
    }

    @Provides
    internal fun provideBun(): WheatBun {
        return WheatBun()
    }

    @Provides
    internal fun providePatty(): BeefPatty {
        return BeefPatty()
    }
}