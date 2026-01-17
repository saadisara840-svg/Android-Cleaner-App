package com.example.applicationdenettoyage


import org.junit.Assert.assertEquals
import org.junit.Test

class StorageUtilsTest {

    @Test
    fun freed_space_is_correct() {
        val freedBytes = 20480
        val freedKb = freedBytes / 1024

        assertEquals(20, freedKb)
    }
}
