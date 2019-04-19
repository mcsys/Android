package com.passionvirus.sample.sample.tddmvvm

import com.passionvirus.sample.sample.tddmvvm.viewmodel.Calculator
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class CalculatorTest {
    var calculator: Calculator? = null

    @Before
    fun setUp() {
        calculator = Calculator()
    }

    @Test
    fun addTest() {
        val result = calculator?.add(15, 10)
        assertEquals(25, result)
    }

    @Test
    fun addTest2() {
        val result = calculator?.add(5, 10)
        assertEquals(15, result)
    }

    @Test
    fun subtractTest() {
        val result = calculator?.subtract(15, 10)
        assertEquals(5, result)
    }
}