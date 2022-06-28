package com.example.a643k

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    var mPermissionResultLauncher: ActivityResultLauncher<Array<String>>? = null
    private var isReadPermissionGranted = false
    private var isLocationPermissionGranted = false
    private var isCameraPermissionGranted = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPermissionResultLauncher = registerForActivityResult(
            RequestMultiplePermissions()
        ) { result ->
            if (result[Manifest.permission.ACCESS_FINE_LOCATION] != null) {
                isReadPermissionGranted = result[Manifest.permission.ACCESS_FINE_LOCATION]!!
            }
            if (result[Manifest.permission.CAMERA] != null) {
                isReadPermissionGranted = result[Manifest.permission.CAMERA]!!
            }
        }
        requestPermission()
    }
    private fun requestPermission() {
        isLocationPermissionGranted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        isCameraPermissionGranted = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
        val permissionRequest: MutableList<String> = ArrayList()
        if (!isLocationPermissionGranted) {
            permissionRequest.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (!isCameraPermissionGranted) {
            permissionRequest.add(Manifest.permission.CAMERA)
        }
        if (!permissionRequest.isEmpty()) {
            mPermissionResultLauncher!!.launch(permissionRequest.toTypedArray())
        }
    }
}