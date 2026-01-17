package com.example.applicationdenettoyage.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.applicationdenettoyage.utils.NotificationUtils
import com.example.applicationdenettoyage.utils.StorageUtils

class CleanWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {

        val freed = StorageUtils.cleanCacheWithProgress(applicationContext) { progress ->
            setProgress(workDataOf("progress" to progress))
            NotificationUtils.showProgress(applicationContext, progress)
            kotlinx.coroutines.delay(200)
        }

        NotificationUtils.showCleaningFinished(applicationContext, freed)

        return Result.success()
    }
}
