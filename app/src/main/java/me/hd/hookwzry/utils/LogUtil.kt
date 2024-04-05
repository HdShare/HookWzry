package me.hd.hookwzry.utils

import de.robv.android.xposed.XposedBridge
import me.hd.hookwzry.BuildConfig

object LogUtil {
    fun stackTrace(): String {
        val sb = StringBuffer()
        val stackElements = Throwable().stackTrace
        for (i in 1 until stackElements.size) {
            val element = stackElements[i]
            sb.append("at ${element.className}.${element.methodName}(${element.fileName}:${element.lineNumber})\n")
        }
        return sb.toString()
    }

    fun logApp(msg: String) {
        val tag = BuildConfig.TAG
        println("[$tag] -> $msg")
    }

    fun logLsp(msg: String) {
        val tag = BuildConfig.TAG
        XposedBridge.log("[$tag] -> $msg")
    }
}