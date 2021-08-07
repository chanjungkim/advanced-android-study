package org.koreanlab.myapp

import org.koreanlab.myapp.`else`.utils.NumberUtil
import junit.framework.TestCase
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
        assertEquals(2, 1 + 1) // This should fail
    }

    @Test
    fun sum_isCorrect() {
        TestCase.assertEquals(2, NumberUtil.sum(1, 1))
    }

    @Test
    fun multiply_isCorrect() {
        TestCase.assertEquals(4, NumberUtil.multiply(2, 2))
    }
}