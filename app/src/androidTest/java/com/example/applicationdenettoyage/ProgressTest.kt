package com.example.applicationdenettoyage

import org.junit.Assert.assertEquals
import org.junit.Test

class ProgressTest {

    @Test
    fun progress_never_exceeds_100() {
        val progress = 120
        val safeProgress = progress.coerceIn(0, 100)

        assertEquals(100, safeProgress)
    }
}
