package com.cheng.httpproject.ui.adapter.base

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.cheng.httpproject.R

class TwoLinesViewHolder(val rootView: View) : RecyclerView.ViewHolder(rootView) {

    val tvLine1 = rootView.findViewById<TextView>(R.id.tv_line_1)
    val tvLine2 = rootView.findViewById<TextView>(R.id.tv_line_2)
    val ivChevron = rootView.findViewById<ImageView>(R.id.iv_chevron_right)

}