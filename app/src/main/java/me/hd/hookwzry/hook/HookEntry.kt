package me.hd.hookwzry.hook

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XSharedPreferences
import de.robv.android.xposed.callbacks.XC_LoadPackage
import me.hd.hookwzry.BuildConfig
import me.hd.hookwzry.hook.handle.Location

class HookEntry : IXposedHookLoadPackage {
    private val prefs = XSharedPreferences(BuildConfig.APPLICATION_ID, "")

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        when (lpparam.packageName) {
            "com.tencent.tmgp.sgame" -> {
                Location.handle(prefs, lpparam)
            }
        }
    }
}