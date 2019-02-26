package com.passionvirus.productivesample

import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class CalculatorTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    @Throws(Exception::class)
    fun add(){
        assertEquals(10, Calculator.add(2, 8))
    }

    @Test
    @Throws(Exception::class)
    fun substract() {
        assertEquals(0, Calculator.subtract(10, 10))
    }

    @Test
    @Throws(Exception::class)
    fun multiply() {
        assertEquals(0, Calculator.multiply(0, 10))
        assertEquals(10, Calculator.multiply(1, 10))
    }

    @Test
    @Throws(Exception::class)
    fun division() {
        assertEquals(2.toFloat(), Calculator.division(5, 2).toFloat(), 0.toFloat())
        assertEquals(2, Calculator.division(4, 2))
    }
}