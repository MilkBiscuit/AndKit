package com.cheng.apikit.util

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.TypedValue
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

object UIUtil {

    fun dpToPx(context: Context, valueInDp: Int): Int {
        val displayMetrics = context.resources.displayMetrics

        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                valueInDp.toFloat(), displayMetrics).toInt()
    }

    fun applyRoundCorner(context: Context, view: View, @ColorRes colorId: Int) {
        val drawableBg = GradientDrawable()
        val color = ContextCompat.getColor(context, colorId)
        val radiusInPx = dpToPx(context, 6)

        drawableBg.setColor(color)
        drawableBg.cornerRadius = radiusInPx.toFloat()
        view.background = drawableBg
    }
}
