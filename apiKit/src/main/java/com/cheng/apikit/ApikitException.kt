package com.cheng.apikit

open class ToolkitException(message: String, cause: Throwable?): RuntimeException(message, cause) {
    constructor(message: String) : this(message, null)
}
