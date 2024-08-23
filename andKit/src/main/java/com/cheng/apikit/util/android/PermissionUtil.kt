package com.cheng.apikit.util.android

import android.Manifest
import android.content.pm.PackageManager


object PermissionUtil {

    fun canReadPhoneState(): Boolean {
        return hasPermission(Manifest.permission.READ_PHONE_STATE)
    }

    fun hasPermission(permission: String): Boolean {
        val appContext = ContextHolder.getAppContext()
        return PackageManager.PERMISSION_GRANTED == appContext.checkSelfPermission(permission)
    }
}
