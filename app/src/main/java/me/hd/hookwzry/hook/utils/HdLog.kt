package me.hd.hookwzry.hook.utils

import de.robv.android.xposed.XposedBridge.log
import me.hd.hookwzry.BuildConfig

object HdLog {
    enum class Status {
        ENABLED,
        DISABLED,
        HOOKED,
        EXCEPTION,
    }

    fun print(msg: String) {
        val tag = BuildConfig.TAG
        log("[$tag] : $msg")
    }

    fun printState(name: String, status: Status) {
        when (status) {
            Status.ENABLED -> print("$name is enabled")
            Status.DISABLED -> print("$name is disabled")
            Status.HOOKED -> print("$name is hooked")
            Status.EXCEPTION -> print("$name has exception")
        }
    }

    fun printStackTrace() {
        val stackElements = Throwable().stackTrace
        val stackString = buildString {
            for (i in 1 until stackElements.size) {
                val element = stackElements[i]
                append("at ${element.className}.${element.methodName}(${element.fileName}:${element.lineNumber})\n")
            }
        }
        print(stackString)
    }
}