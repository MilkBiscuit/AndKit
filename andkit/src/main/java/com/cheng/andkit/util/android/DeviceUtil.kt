package com.cheng.andkit.util.android

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.provider.Settings.Secure
import android.telephony.TelephonyManager


object DeviceUtil {
    private const val TAG = "DeviceUtil"
    private var deviceId: String? = null

    private val resources: Resources by lazy {
        Resources.getSystem()
    }

    val screenWidthInDp: Int
        get() {
            val widthFloat: Float = resources.displayMetrics.widthPixels / resources.displayMetrics.density
            return widthFloat.toInt()
        }

    // This height does NOT include the navigation bar so it is slightly smaller than the actual value
    // See also: hasNavigationBar()
    val screenHeightInDp: Int
        get() {
            val heightFloat: Float = resources.displayMetrics.heightPixels / resources.displayMetrics.density
            return heightFloat.toInt()
        }

    /**
     * Obtains the device ID from the Android OS
     */
    @SuppressLint("HardwareIds")
    fun getDeviceId(): String? {
        val haveCachedValue = deviceId != null
        if (haveCachedValue) {
            return deviceId
        }
        val context: Context = ContextHolder.getAppContext()
        val androidId = Secure.getString(context.contentResolver, Secure.ANDROID_ID)
        deviceId = if (androidId.isNullOrEmpty()) {
            if (PermissionUtil.canReadPhoneState()) {
                getTelephonyDeviceId()
            } else {
                ""
            }
        } else {
            androidId
        }

        if (deviceId.isNullOrEmpty()) {
            throw RuntimeException("Unable to obtain device ID...")
        }
        return deviceId
    }

    // TODO: needs test on a real device with SIM card
    @SuppressLint("HardwareIds")
    fun getTelephonyDeviceId(): String? {
        val context: Context = ContextHolder.getAppContext()
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

    fun getScreenResolutionInPxString(): String {
        val metrics = resources.displayMetrics
        val width = metrics.widthPixels.toString()
        val height = metrics.heightPixels.toString()

        return "${width}x${height}"
    }

    fun isTablet(thresholdInDp: Int = 600) = resources.configuration.smallestScreenWidthDp >= thresholdInDp

    fun isSmallScreen(thresholdInDp: Int = 320) = resources.configuration.smallestScreenWidthDp <= thresholdInDp

    fun isTabletInLandscape(thresholdInDp: Int = 600) =
        isTablet(thresholdInDp) && (Configuration.ORIENTATION_LANDSCAPE == resources.configuration.orientation)

    fun hasNavigationBar(): Boolean {
        // For Build.VERSION_CODES.JELLY_BEAN_MR1 and above, this is normally true
        val id = resources.getIdentifier("config_showNavigationBar", "bool", "android")

        return id > 0 && resources.getBoolean(id)
    }

    fun isRunningUnitTest(): Boolean {
        val deviceName = Build.DEVICE
        val productName = Build.PRODUCT
        val robolectricName = "robolectric"

        return (deviceName == null && productName == null)
                || (deviceName == robolectricName && productName == robolectricName)
    }

}
