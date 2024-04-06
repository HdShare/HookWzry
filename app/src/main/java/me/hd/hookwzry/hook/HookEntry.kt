package me.hd.hookwzry.hook

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage
import me.hd.hookwzry.data.ConstData
import me.hd.hookwzry.hook.handle.MapLocation
import me.hd.hookwzry.hook.handle.RealName

class HookEntry : IXposedHookLoadPackage {
    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        when (lpparam.packageName) {
            ConstData.PM_WZRY -> {
                MapLocation().handle(lpparam)
                RealName().handle(lpparam)
            }
        }
    }
}