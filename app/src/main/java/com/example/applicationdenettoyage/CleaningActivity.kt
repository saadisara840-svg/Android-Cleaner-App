package com.example.applicationdenettoyage

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.example.applicationdenettoyage.worker.CleanWorker
import java.util.UUID

class CleaningActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var txtProgress: TextView
    private lateinit var txtResult: TextView

    private var cleaningWorkId: UUID? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cleaning)

        progressBar = findViewById(R.id.progressBar)
        txtProgress = findViewById(R.id.txtProgress)
        txtResult = findViewById(R.id.txtResult)

        startCleaning()
    }

    private fun startCleaning() {

        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(false)
            .setRequiresStorageNotLow(true)
            .build()

        val work = OneTimeWorkRequestBuilder<CleanWorker>()
            .setConstraints(constraints)
            .build()

        cleaningWorkId = work.id

        WorkManager.getInstance(this).enqueue(work)

        observeProgress()
    }


    private fun observeProgress() {
        cleaningWorkId?.let { id ->
            WorkManager.getInstance(this)
                .getWorkInfoByIdLiveData(id)
                .observe(this) { info ->
                    info?.let {
                        val progress = it.progress.getInt("progress", 0)
                        progressBar.progress = progress
                        txtProgress.text = "$progress %"

                        if (it.state.isFinished) {
                            txtResult.text = "Nettoyage terminé"
                        }
                    }
                }
        }
    }
}
