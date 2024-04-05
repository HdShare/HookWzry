package me.hd.hookwzry.hook.handle

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XSharedPreferences
import de.robv.android.xposed.XposedHelpers.callMethod
import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.callbacks.XC_LoadPackage
import me.hd.hookwzry.utils.LogUtil

object RealName {
    fun handle(prefs: XSharedPreferences, lpparam: XC_LoadPackage.LoadPackageParam) {
        if (prefs.getBoolean("RealName", true)) {
            LogUtil.logLsp("RealName is enabled")
            findAndHookMethod(
                "com.tencent.msdk.realnameauth.RealNameAuthManager",
                lpparam.classLoader,
                "startRealNameProcessInMainThread",
                object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        callMethod(param.thisObject, "onRealNameAuthNotify", 0, "")
                        param.result = null
                        LogUtil.logLsp("RealName is hooked")
                    }
                }
            )
        } else {
            LogUtil.logLsp("RealName is disabled")
        }
    }
}