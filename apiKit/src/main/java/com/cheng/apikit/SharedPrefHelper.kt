package com.cheng.apikit

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.cheng.apikit.util.ContextUtil
import java.io.IOException


object SharedPrefHelper {

    private const val PREFERENCES_NAME_TOOLKIT = "ApiKit"
    private val encryptedPreferences: HashMap<String, SharedPreferences?> = HashMap()
    val defaultSharedPref: SharedPreferences by lazy {
        val encryptedSharedPref = getEncryptedSharedPref(PREFERENCES_NAME_TOOLKIT)
            ?: throw ApikitException("Can not create encrypted shared preferences!")

        encryptedSharedPref
    }

    fun getEncryptedSharedPref(sharedPreferencesName: String): SharedPreferences? {
        if (encryptedPreferences.containsKey(sharedPreferencesName)) {
            return encryptedPreferences[sharedPreferencesName]
        }
        val context: Context = ContextUtil.getAppContext()
        var sharedPreferences: SharedPreferences? = null
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        try {
            sharedPreferences = EncryptedSharedPreferences.create(
                sharedPreferencesName,
                masterKeyAlias,
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } catch (e: IOException) {
            e.printStackTrace()

            /**
             * When the App set android:allowBackup to true, encrypted xml file will be restored and will encounter
             * InvalidProtocolBufferException: Protocol message contained an invalid tag (zero).
             *
             * In that case, clear all the content in the xml and re-create encrypted shared preferences
             */
            context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .commit()
            sharedPreferences = EncryptedSharedPreferences.create(
                sharedPreferencesName,
                masterKeyAlias,
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        }

        // Fall back to standard shared preferences
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)
        }
        encryptedPreferences[sharedPreferencesName] = sharedPreferences

        return sharedPreferences
    }

}
