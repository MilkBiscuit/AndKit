package com.cheng.andkit.model

open class ApikitException(message: String, cause: Throwable?): RuntimeException(message, cause) {
    constructor(message: String) : this(message, null)
}
