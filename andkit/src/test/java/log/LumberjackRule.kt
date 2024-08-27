package log

import com.cheng.andkit.log.Lumberjack
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class LumberjackRule: TestWatcher() {

    private val printlnTree = object : Lumberjack.DebugTree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            println("$tag: $message")
        }
    }

    override fun starting(description: Description?) {
        super.starting(description)
        Lumberjack.plant(printlnTree)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Lumberjack.uproot(printlnTree)
    }

}
