package com.cheng.apikit.util.android

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import java.io.IOException


object SharedPrefHelper {

    private val encryptedPreferences: HashMap<String, SharedPreferences?> = HashMap()

    fun getEncryptedSharedPref(sharedPreferencesName: String): SharedPreferences? {
        if (encryptedPreferences.containsKey(sharedPreferencesName)) {
            return encryptedPreferences[sharedPreferencesName]
        }
        val context: Context = ContextHolder.getAppContext()
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
                .apply()
            sharedPreferences = EncryptedSharedPreferences.create(
                sharedPreferencesName,
                masterKeyAlias,
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        }

        encryptedPreferences[sharedPreferencesName] = sharedPreferences

        return sharedPreferences
    }

}
