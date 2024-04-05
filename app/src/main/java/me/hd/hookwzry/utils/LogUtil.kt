package me.hd.hookwzry.utils

import android.util.Log
import de.robv.android.xposed.XposedBridge
import me.hd.hookwzry.BuildConfig

object LogUtil {
    fun logApp(msg: String) {
        val tag = BuildConfig.TAG
        println("[$tag] -> $msg")
    }

    fun logLsp(msg: String) {
        val tag = BuildConfig.TAG
        XposedBridge.log("[$tag] -> $msg")
    }
}