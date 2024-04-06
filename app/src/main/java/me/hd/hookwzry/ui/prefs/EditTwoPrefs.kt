package me.hd.hookwzry.ui.prefs

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import me.hd.hookwzry.R
import me.hd.hookwzry.databinding.PrefsDialogEditTwoBinding
import me.hd.hookwzry.databinding.PrefsEditTwoBinding
import me.hd.hookwzry.ui.utils.Prefs

class EditTwoPrefs(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.EditTwoPrefs)
        val title = attributes.getString(R.styleable.EditTwoPrefs_title)
        val summary = attributes.getString(R.styleable.EditTwoPrefs_summary)
        val hint1 = attributes.getString(R.styleable.EditTwoPrefs_hint1)
        val key1 = attributes.getString(R.styleable.EditTwoPrefs_key1)
        val defValStr1 = attributes.getString(R.styleable.EditTwoPrefs_defVal1)
        val hint2 = attributes.getString(R.styleable.EditTwoPrefs_hint2)
        val key2 = attributes.getString(R.styleable.EditTwoPrefs_key2)
        val defValStr2 = attributes.getString(R.styleable.EditTwoPrefs_defVal2)
        attributes.recycle()

        val binding = PrefsEditTwoBinding.inflate(LayoutInflater.from(context), this, true)
        title?.let { binding.prefsEditTitle.text = it }
        summary?.let { binding.prefsEditSummary.text = it }
        key1?.let {
            val defVal = defValStr1?.toFloat() ?: 0.00000f
            val value = Prefs.getVal(context, it, defVal) as Float
            Prefs.putVal(context, it, value)
        }
        key2?.let {
            val defVal = defValStr2?.toFloat() ?: 0.00000f
            val value = Prefs.getVal(context, it, defVal) as Float
            Prefs.putVal(context, it, value)
        }
        binding.prefsEditView.setOnClickListener {
            val dialogBinding = PrefsDialogEditTwoBinding.inflate(LayoutInflater.from(context))
            dialogBinding.prefsDialogEditTwoLayout1.hint = hint1
            key1?.let {
                val defVal = defValStr1?.toFloat() ?: 0.00000f
                val defEdt1 = Prefs.getVal(context, key1, defVal) as Float
                dialogBinding.prefsDialogEditTwoEdt1.setText(defEdt1.toString())
            }
            dialogBinding.prefsDialogEditTwoLayout2.hint = hint2
            key2?.let {
                val defVal = defValStr2?.toFloat() ?: 0.00000f
                val defEdt2 = Prefs.getVal(context, key2, defVal) as Float
                dialogBinding.prefsDialogEditTwoEdt2.setText(defEdt2.toString())
            }
            MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setView(dialogBinding.root)
                .setPositiveButton("Accept") { _, _ ->
                    val edt1 = dialogBinding.prefsDialogEditTwoEdt1.text.toString()
                    val edt2 = dialogBinding.prefsDialogEditTwoEdt2.text.toString()
                    if (edt1.isNotEmpty() && edt2.isNotEmpty()) {
                        key1?.let { Prefs.putVal(context, it, edt1.toFloat()) }
                        key2?.let { Prefs.putVal(context, it, edt2.toFloat()) }
                    }
                }
                .setNegativeButton("Decline") { _, _ -> }
                .show()
        }
    }
}