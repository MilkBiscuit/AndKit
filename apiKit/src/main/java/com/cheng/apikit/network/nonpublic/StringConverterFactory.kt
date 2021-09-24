package com.cheng.apikit.network.nonpublic

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

internal class StringConverterFactory: Converter.Factory() {

    override fun responseBodyConverter(
        type: Type, annotations: Array<Annotation?>?, retrofit: Retrofit?
    ): Converter<ResponseBody, String?>? {
        return if (type === String::class.java) {
            Converter { obj: ResponseBody -> obj.string() }
        } else null
    }

}
