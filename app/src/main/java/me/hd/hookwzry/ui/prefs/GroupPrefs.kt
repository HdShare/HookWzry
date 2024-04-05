package me.hd.hookwzry.ui.prefs

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import me.hd.hookwzry.R
import me.hd.hookwzry.databinding.PrefsGroupBinding

class GroupPrefs(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.GroupPrefs)
        val title = typedArray.getString(R.styleable.GroupPrefs_title)
        typedArray.recycle()

        val binding = PrefsGroupBinding.inflate(LayoutInflater.from(context), this, true)
        title?.let { binding.prefsGroupTitle.text = it }
    }
}