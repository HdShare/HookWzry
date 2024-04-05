package me.hd.hookwzry.ui.prefs

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import me.hd.hookwzry.R
import me.hd.hookwzry.databinding.PrefsSwitchBinding
import me.hd.hookwzry.utils.LogUtil
import me.hd.hookwzry.utils.PrefsUtil

class SwitchPrefs(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SwitchPrefs)
        val key = typedArray.getString(R.styleable.SwitchPrefs_key)
        val defValStr = typedArray.getString(R.styleable.SwitchPrefs_defVal)
        val title = typedArray.getString(R.styleable.SwitchPrefs_title)
        val summary = typedArray.getString(R.styleable.SwitchPrefs_summary)
        typedArray.recycle()

        val binding = PrefsSwitchBinding.inflate(LayoutInflater.from(context), this, true)
        title?.let { binding.prefsSwitchTitle.text = it }
        summary?.let { binding.prefsSwitchSummary.text = it }
        key?.let {
            val defVal = defValStr?.toBoolean() ?: false
            val isChecked = PrefsUtil.getVal(context, it, defVal) as Boolean
            binding.prefsSwitchSwitch.isChecked = isChecked
            PrefsUtil.putVal(context, it, isChecked)
        }
        binding.prefsSwitchView.setOnClickListener {
            binding.prefsSwitchSwitch.isChecked = !binding.prefsSwitchSwitch.isChecked
            key?.let { PrefsUtil.putVal(context, it, binding.prefsSwitchSwitch.isChecked) }
        }
    }
}