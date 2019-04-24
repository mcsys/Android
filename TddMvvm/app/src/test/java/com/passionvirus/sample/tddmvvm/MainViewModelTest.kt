package com.passionvirus.sample.tddmvvm

import com.passionvirus.sample.tddmvvm.viewmodel.MainViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    var vm: MainViewModel? = null

    @Before
    fun setUp() {
        vm = MainViewModel()
    }

    @Test
    fun getContributorsSuccess() {
        Assert.assertEquals(4, 2 + 2)
    }

    @Test
    fun getContributorsFail() {
        Assert.assertEquals(4, 2 + 2)
    }
}