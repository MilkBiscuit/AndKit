package com.cheng.apikit

open class ApikitException(message: String, cause: Throwable?): RuntimeException(message, cause) {
    constructor(message: String) : this(message, null)
}
