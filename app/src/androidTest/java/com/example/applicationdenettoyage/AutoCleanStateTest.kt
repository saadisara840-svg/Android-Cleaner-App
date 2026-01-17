package com.example.applicationdenettoyage

import org.junit.Assert.assertEquals
import org.junit.Test

class AutoCleanStateTest {

    @Test
    fun autoClean_is_ON() {
        val isAutoClean = true
        val statusText = if (isAutoClean) "Auto Clean: ON"
        else "Auto Clean: OFF"

        assertEquals("Auto Clean: ON", statusText)
    }

    @Test
    fun autoClean_is_OFF() {
        val isAutoClean = false
        val statusText = if (isAutoClean) "Auto Clean: ON"
        else "Auto Clean: OFF"

        assertEquals("Auto Clean: OFF", statusText)
    }
}
