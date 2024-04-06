package me.hd.hookwzry.hook.handle

import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers.findAndHookMethod
import de.robv.android.xposed.callbacks.XC_LoadPackage
import me.hd.hookwzry.data.ConstData
import me.hd.hookwzry.hook.utils.HdLog
import me.hd.hookwzry.hook.utils.HdLog.Status
import me.hd.hookwzry.hook.utils.XPrefs

class MapLocation {
    private fun getMethodParam(longitude: Float, latitude: Float): String {
        return """{"callbackTag":1"code":0,"desc":"success","errorCode":0,"fakeType":0,"latitude":"$latitude","longtitude":"$longitude","nation":"null","province":"null","city":"null","district":"null"}"""
    }

    fun handle(lpparam: XC_LoadPackage.LoadPackageParam) {
        if (XPrefs.getBoolean(ConstData.MAP_LOCATION, false)) {
            HdLog.printState(ConstData.MAP_LOCATION, Status.ENABLED)
            findAndHookMethod(
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
                            val longitude = XPrefs.getFloat(ConstData.LONGITUDE, 0.00000f)
                            val latitude = XPrefs.getFloat(ConstData.LATITUDE, 0.00000f)
                            if (longitude != 0.00000f && latitude != 0.00000f) {
                                val methodParam = getMethodParam(longitude, latitude)
                                param.args[2] = methodParam
                                HdLog.printState(ConstData.MAP_LOCATION, Status.HOOKED)
                            } else {
                                HdLog.printState(ConstData.MAP_LOCATION, Status.EXCEPTION)
                            }
                        }
                    }
                }
            )
        } else {
            HdLog.printState(ConstData.MAP_LOCATION, Status.DISABLED)
        }
    }
}