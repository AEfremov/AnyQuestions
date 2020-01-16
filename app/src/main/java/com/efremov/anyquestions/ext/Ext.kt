package com.efremov.anyquestions.ext

import android.content.res.Resources
import android.os.Build
import android.view.View
import androidx.fragment.app.Fragment
import com.efremov.anyquestions.core.FragmentArgumentDelegate
import com.efremov.anyquestions.core.FragmentNullableArgumentDelegate
import kotlin.properties.ReadWriteProperty

fun <T : Any> argument() : ReadWriteProperty<Fragment, T> =
    FragmentArgumentDelegate()

fun <T : Any> argumentNullable() : ReadWriteProperty<Fragment, T?> =
    FragmentNullableArgumentDelegate()

fun Resources.color(colorRes: Int) =
    if (Build.VERSION.SDK_INT >= 23) {
        this.getColor(colorRes, null)
    } else {
        @Suppress("DEPRECATION")
        this.getColor(colorRes)
    }

fun View.visible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}