package me.hd.hookwzry.hook.utils

import de.robv.android.xposed.XSharedPreferences
import me.hd.hookwzry.BuildConfig

object XPrefs {
    private val prefs = XSharedPreferences(BuildConfig.APPLICATION_ID)

    fun getString(key: String, defVal: String): String? {
        return prefs.getString(key, defVal)
    }

    fun getInt(key: String, defVal: Int): Int {
        return prefs.getInt(key, defVal)
    }

    fun getBoolean(key: String, defVal: Boolean): Boolean {
        return prefs.getBoolean(key, defVal)
    }

    fun getFloat(key: String, defVal: Float): Float {
        return prefs.getFloat(key, defVal)
    }

    fun getLong(key: String, defVal: Long): Long {
        return prefs.getLong(key, defVal)
    }
}