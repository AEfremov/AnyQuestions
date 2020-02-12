package com.efremov.anyquestions.ext

import android.content.Context
import androidx.core.content.ContextCompat

fun Context.color(colorRes: Int) = ContextCompat.getColor(this, colorRes)