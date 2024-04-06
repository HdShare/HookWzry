package me.hd.hookwzry.hook.handle

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers.callMethod
import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.callbacks.XC_LoadPackage
import me.hd.hookwzry.data.ConstData
import me.hd.hookwzry.hook.utils.HdLog
import me.hd.hookwzry.hook.utils.HdLog.Status
import me.hd.hookwzry.hook.utils.XPrefs

class RealName {
    fun handle(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (XPrefs.getBoolean(ConstData.REAL_NAME, false)) {
            HdLog.printState(ConstData.REAL_NAME, Status.ENABLED)
            findAndHookMethod(
                "com.tencent.msdk.realnameauth.RealNameAuthManager",
                lpparam.classLoader,
                "startRealNameProcessInMainThread",
                object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        callMethod(param.thisObject, "onRealNameAuthNotify", 0, "")
                        param.result = null
                        HdLog.printState(ConstData.REAL_NAME, Status.HOOKED)
                    }
                }
            )
        } else {
            HdLog.printState(ConstData.REAL_NAME, Status.DISABLED)
        }
    }
}