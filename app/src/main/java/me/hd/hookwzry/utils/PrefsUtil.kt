package me.hd.hookwzry.utils

import android.content.Context
import android.content.SharedPreferences

object PrefsUtil {
    private var prefs: SharedPreferences? = null

    private fun getPrefs(context: Context): SharedPreferences {
        if (prefs == null) {
            prefs = context.getSharedPreferences(context.packageName, Context.MODE_WORLD_READABLE)
        }
        return prefs!!
    }

    fun putVal(context: Context, key: String, value: Any) {
        val prefs = getPrefs(context)
        val editor = prefs.edit()
        when (value) {
            is String -> editor.putString(key, value)
            is Int -> editor.putInt(key, value)
            is Boolean -> editor.putBoolean(key, value)
            is Float -> editor.putFloat(key, value)
            is Long -> editor.putLong(key, value)
        }
        editor.apply()
    }

    fun getVal(context: Context, key: String, defVal: Any): Any? {
        val prefs = getPrefs(context)
        return when (defVal) {
            is String -> prefs.getString(key, defVal)
            is Int -> prefs.getInt(key, defVal)
            is Boolean -> prefs.getBoolean(key, defVal)
            is Float -> prefs.getFloat(key, defVal)
            is Long -> prefs.getLong(key, defVal)
            else -> null
        }
    }
}