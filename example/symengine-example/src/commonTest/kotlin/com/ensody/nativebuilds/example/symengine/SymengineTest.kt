package com.ensody.nativebuilds.example.symengine

import kotlin.test.Test
import kotlin.test.assertEquals

internal class SymengineTest {
    @Test
    fun testVersion() {
        assertEquals("1.5.7", getSymengineVersion())
    }
}
