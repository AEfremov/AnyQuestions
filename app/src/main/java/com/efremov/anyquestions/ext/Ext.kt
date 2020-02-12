package com.efremov.anyquestions.ext

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.efremov.anyquestions.core.FragmentArgumentDelegate
import com.efremov.anyquestions.core.FragmentNullableArgumentDelegate
import kotlin.properties.ReadWriteProperty

fun <T : Any> argument() : ReadWriteProperty<Fragment, T> =
    FragmentArgumentDelegate()

fun <T : Any> argumentNullable() : ReadWriteProperty<Fragment, T?> =
    FragmentNullableArgumentDelegate()

fun View.visible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun Context.color(colorRes: Int) = ContextCompat.getColor(this, colorRes)