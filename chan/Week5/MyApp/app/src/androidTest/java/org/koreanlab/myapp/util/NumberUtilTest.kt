package org.koreanlab.myapp.util

import org.koreanlab.myapp.`else`.utils.NumberUtil
import junit.framework.TestCase

class NumberUtilTest : TestCase() {
    fun testSum() {
        assertEquals(2, NumberUtil.sum(1,1))
    }

    fun testMultiply() {
        assertEquals(4, NumberUtil.multiply(2,2))
    }
}