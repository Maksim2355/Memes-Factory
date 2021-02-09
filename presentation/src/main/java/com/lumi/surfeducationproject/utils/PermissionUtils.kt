package com.lumi.surfeducationproject.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.lumi.surfeducationproject.common.REQUEST_CODE_PERMISSION_GALLERY


fun AppCompatActivity.requestPermission(permission: String) {
    ActivityCompat.requestPermissions(
        this,
        arrayOf(
            permission
        ),
        REQUEST_CODE_PERMISSION_GALLERY
    )
}

fun Context.checkPermission(permission: String): Boolean =
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED