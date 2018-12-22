package com.cheng.httpproject.util

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.util.DisplayMetrics
import android.view.View

object UIUtil {

    fun dpToPx(context: Context, dp: Int): Int {
        val displayMetrics = context.resources.displayMetrics

        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
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