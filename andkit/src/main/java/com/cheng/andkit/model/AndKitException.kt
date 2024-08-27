package com.cheng.andkit.model

open class AndKitException(message: String, cause: Throwable?): RuntimeException(message, cause) {
    constructor(message: String) : this(message, null)
}
