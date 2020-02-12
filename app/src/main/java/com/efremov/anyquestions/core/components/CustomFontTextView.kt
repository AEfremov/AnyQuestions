package com.efremov.anyquestions.core.components

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.widget.TextView
import com.efremov.anyquestions.core.FontManager
import com.efremov.anyquestions.R

class CustomFontTextView : TextView {

    companion object {
        const val OTF = "otf"
        const val TTF = "ttf"
    }

    constructor(context: Context) : this(context, null) {
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        if (isInEditMode) return
        val typedArray: TypedArray = context!!.obtainStyledAttributes(attrs,
            R.styleable.CustomFontTextView
        )
        val fontAsset = typedArray.getString(R.styleable.CustomFontTextView_typeFaceAsset)
        if (fontAsset != null) {
            if (fontAsset.isNotEmpty()) {
                var tf: Typeface? = null
                if (fontAsset == "ttf") {
                    tf = FontManager.getFont(
                        fontAsset,
                        TTF
                    )
                } else {
                    tf = FontManager.getFont(
                        fontAsset,
                        OTF
                    )
                }
                var style = Typeface.NORMAL
                val size = textSize
                if (typeface != null) {
                    style = typeface.style
                }
                if (tf != null) {
                    setTypeface(tf, style)
                } else {
                    Log.d("CustomFontTextView", String.format("Could not create a font from asset: %s", fontAsset))
                }
            }
        }
//        val textValue = typedArray.getString(R.styleable.CustomFontTextView_textValue)
//        if (textValue != null) {
//            text = textValue
//        }

        typedArray.recycle()
    }
}