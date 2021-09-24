package com.cheng.apikit.util

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings.Secure
import android.telephony.TelephonyManager


object DeviceUtil {
    private const val TAG = "DeviceUtil"
    private var sDeviceId: String? = null

    /**
     * Obtains the device ID from the Android OS
     */
    // TODO: encrypt device ID
    @SuppressLint("HardwareIds")
    fun getDeviceId(): String? {
        val haveCachedValue = sDeviceId != null
        if (haveCachedValue) {
            return sDeviceId
        }
        val context: Context = ContextUtil.getAppContext()
        val androidId = Secure.getString(context.contentResolver, Secure.ANDROID_ID)
        sDeviceId = if (androidId.isNullOrEmpty()) {
            if (PermissionUtil.canReadPhoneState()) {
                getTelephonyDeviceId()
            } else {
                ""
            }
        } else {
            androidId
        }

        if (sDeviceId.isNullOrEmpty()) {
            throw RuntimeException("Unable to obtain device ID...")
        }
        return sDeviceId
    }

    // TODO: needs test on a real device with SIM card
    @SuppressLint("HardwareIds")
    fun getTelephonyDeviceId(): String? {
        val context: Context = ContextUtil.getAppContext()
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as? TelephonyManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            when (telephonyManager?.phoneType) {
                TelephonyManager.PHONE_TYPE_CDMA -> telephonyManager.meid
                TelephonyManager.PHONE_TYPE_GSM -> telephonyManager.imei
                else -> null
            }
        } else {
            telephonyManager?.deviceId
        }
    }

    fun getScreenResolution(): String {
        val context: Context = ContextUtil.getAppContext()
        val metrics = context.resources.displayMetrics
        val width = metrics.widthPixels.toString()
        val height = metrics.heightPixels.toString()

        return "${width}x${height}"
    }

    fun isRunningPureUnitTest(): Boolean {
        val deviceName = Build.DEVICE
        val productName = Build.PRODUCT
        val robolectricName = "robolectric"

        return (deviceName == null && productName == null)
                || (deviceName == robolectricName && productName == robolectricName)
    }

}
