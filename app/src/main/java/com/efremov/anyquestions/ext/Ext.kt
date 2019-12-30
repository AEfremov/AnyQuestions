package com.efremov.anyquestions.ext

import androidx.fragment.app.Fragment
import com.efremov.anyquestions.core.FragmentArgumentDelegate
import com.efremov.anyquestions.core.FragmentNullableArgumentDelegate
import kotlin.properties.ReadWriteProperty

fun <T : Any> argument() : ReadWriteProperty<Fragment, T> =
    FragmentArgumentDelegate()

fun <T : Any> argumentNullable() : ReadWriteProperty<Fragment, T?> =
    FragmentNullableArgumentDelegate()