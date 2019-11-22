package com.cheng.httpproject.helper

import android.content.Context
import androidx.annotation.StringRes

class ResStringHelper private constructor(context: Context) {

    val context = context.applicationContext

    companion object: SingletonHolder<ResStringHelper, Context>(::ResStringHelper)

    fun getString(@StringRes stringId: Int, vararg format: Any): String =
            context.getString(stringId, *format)

}