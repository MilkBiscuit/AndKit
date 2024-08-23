package android.util

object Log {
    @JvmStatic
    fun v(tag: String, msg: String): Int {
        println("VERBOSE: $tag: $msg")
        return 0
    }

    @JvmStatic
    fun d(tag: String, msg: String): Int {
        println("DEBUG: $tag: $msg")
        return 0
    }

    @JvmStatic
    fun i(tag: String, msg: String): Int {
        println("INFO: $tag: $msg")
        return 0
    }

    @JvmStatic
    fun w(tag: String, msg: String): Int {
        println("WARN: $tag: $msg")
        return 0
    }

    @JvmStatic
    fun e(tag: String, msg: String): Int {
        println("ERROR: $tag: $msg")
        return 0
    }

    @JvmStatic
    fun e(tag: String, msg: String, throwable: Throwable): Int {
        println("ERROR: $tag: $msg, ${throwable.message}")
        return 0
    }

    @JvmStatic
    fun e(tag: String, throwable: Throwable): Int {
        println("ERROR: $tag: ${throwable.message}")
        return 0
    }
}
