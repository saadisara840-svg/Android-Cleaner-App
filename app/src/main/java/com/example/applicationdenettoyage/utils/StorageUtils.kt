package com.example.applicationdenettoyage.utils

import android.content.Context

object StorageUtils {

    suspend fun cleanCacheWithProgress(
        context: Context,
        onProgress: suspend (Int) -> Unit
    ): Long {

        val files = context.cacheDir.listFiles() ?: return 0L
        if (files.isEmpty()) return 0L

        var freed = 0L
        val total = files.size
        var count = 0

        for (file in files) {
            freed += file.length()
            file.delete()
            count++

            val progress = (count * 100) / total
            onProgress(progress) // ✅ دابا مسموح
        }
        return freed
    }
}
