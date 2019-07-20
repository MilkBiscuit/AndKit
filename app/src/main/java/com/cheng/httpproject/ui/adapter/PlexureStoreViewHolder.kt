package com.cheng.httpproject.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.cheng.httpproject.R

class PlexureStoreViewHolder(val rootView: View) : RecyclerView.ViewHolder(rootView) {

    val tvName = rootView.findViewById<TextView>(R.id.tv_name)
    val tvAddress = rootView.findViewById<TextView>(R.id.tv_address)
    val tvDistance = rootView.findViewById<TextView>(R.id.tv_distance)
    val tvFeature = rootView.findViewById<TextView>(R.id.tv_feature)
    val ivFavorite = rootView.findViewById<ImageView>(R.id.iv_favorite)

}