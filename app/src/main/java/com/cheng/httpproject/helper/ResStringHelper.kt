package com.cheng.httpproject.helper

import android.content.Context
import android.support.annotation.StringRes
import com.cheng.httpproject.util.SingletonHolder

class ResStringHelper private constructor(context: Context) {

    val context = context.applicationContext

    companion object: SingletonHolder<ResStringHelper, Context>(::ResStringHelper)

    fun getString(@StringRes stringId: Int, vararg format: Any): String =
            context.getString(stringId, *format)

}