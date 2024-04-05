package me.hd.hookwzry.hook.handle

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XSharedPreferences
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
import me.hd.hookwzry.utils.LogUtil

object Location {
    private fun getMethodParam(latitude: Double, longtitude: Double): String {
        return """{"callbackTag":1"code":0,"desc":"success","errorCode":0,"fakeType":0,"latitude":"$latitude","longtitude":"$longtitude","nation":"null","province":"null","city":"null","district":"null"}"""
    }

    fun handle(prefs: XSharedPreferences, lpparam: XC_LoadPackage.LoadPackageParam) {
        if (prefs.getBoolean("location", true)) {
            LogUtil.logLsp("Location is enabled")
            XposedHelpers.findAndHookMethod(
                "com.unity3d.player.UnityPlayer",
                lpparam.classLoader,
                "UnitySendMessage",
                String::class.java,
                String::class.java,
                String::class.java,
                object : XC_MethodHook() {
                    override fun beforeHookedMethod(param: MethodHookParam) {
                        val gameObject = param.args[0] as String
                        val methodName = param.args[1] as String
                        if (gameObject == "MapService" && methodName == "OnGetMapLocation") {
                            val methodParam = getMethodParam(22.160511, 113.586097)
                            param.args[2] = methodParam
                            LogUtil.logLsp("Location is hooked")
                        }
                    }
                }
            )
        } else {
            LogUtil.logLsp("Location is disabled")
        }
    }
}