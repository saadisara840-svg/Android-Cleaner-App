package com.example.applicationdenettoyage

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import com.example.applicationdenettoyage.utils.NotificationUtils
import com.example.applicationdenettoyage.worker.CleanWorker
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(),
    EasyPermissions.PermissionCallbacks {

    companion object {
        private const val STORAGE_PERMISSION_CODE = 100
        private const val NOTIFICATION_PERMISSION_CODE = 200
    }

    private lateinit var btnClean: Button
    private lateinit var txtResult: TextView
    private lateinit var btnStartAuto: Button
    private lateinit var btnStopAuto: Button
    private lateinit var txtAutoStatus: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnClean = findViewById(R.id.btnClean)
        btnStartAuto = findViewById(R.id.btnStartAuto)
        btnStopAuto = findViewById(R.id.btnStopAuto)
        txtResult = findViewById(R.id.txtResult)
        txtAutoStatus = findViewById(R.id.txtAutoStatus)

        requestNotificationPermission()

        btnClean.setOnClickListener {
            checkStoragePermission()
        }

        btnStartAuto.setOnClickListener {
            startAutoClean()
            txtResult.text = "Auto nettoyage activé"
        }

        btnStopAuto.setOnClickListener {
            stopAutoClean()
            txtResult.text = "Auto nettoyage arrêté"
        }

        updateAutoCleanStatus()
    }

    private fun checkStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            openCleaningActivity()
            return
        }

        if (EasyPermissions.hasPermissions(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        ) {
            openCleaningActivity()
        } else {
            EasyPermissions.requestPermissions(
                this,
                "Permission stockage requise",
                STORAGE_PERMISSION_CODE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
    }

    private fun openCleaningActivity() {
        val intent = Intent(this, CleaningActivity::class.java)
        startActivity(intent)
    }

    private fun startAutoClean() {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .build()

        val autoWork = PeriodicWorkRequestBuilder<CleanWorker>(
            24, TimeUnit.HOURS
        )
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "auto_clean",
            ExistingPeriodicWorkPolicy.REPLACE,
            autoWork
        )

        saveAutoCleanState(true)
        updateAutoCleanStatus()
        NotificationUtils.showAutoCleanStatus(this, true)

    }

    private fun stopAutoClean() {
        WorkManager.getInstance(this)
            .cancelUniqueWork("auto_clean")

        saveAutoCleanState(false)
        updateAutoCleanStatus()
        NotificationUtils.showAutoCleanStatus(this, false)

       // NotificationUtils.cancelAutoCleanStatus(this)

    }

    private fun saveAutoCleanState(enabled: Boolean) {
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        prefs.edit().putBoolean("AUTO_CLEAN", enabled).apply()
    }

    private fun isAutoCleanEnabled(): Boolean {
        val prefs = getSharedPreferences("settings", MODE_PRIVATE)
        return prefs.getBoolean("AUTO_CLEAN", false)
    }

    private fun updateAutoCleanStatus() {
        if (isAutoCleanEnabled()) {
            txtAutoStatus.text = "Auto Clean: ON"
        } else {
            txtAutoStatus.text = "Auto Clean: OFF"
        }
    }


    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED
            ) {
                requestPermissions(
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    NOTIFICATION_PERMISSION_CODE
                )
            }
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        if (requestCode == STORAGE_PERMISSION_CODE) {
            openCleaningActivity()
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(
            requestCode, permissions, grantResults, this
        )
    }
}
