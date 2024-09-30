package com.cheng.andkit.sample.ui.store

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.cheng.andkit.sample.R

class PlexureStoreViewHolder(private val rootView: View) : RecyclerView.ViewHolder(rootView) {

    val tvName: TextView = rootView.findViewById<TextView>(R.id.tv_name)
    val tvAddress: TextView = rootView.findViewById<TextView>(R.id.tv_address)
    val tvDistance: TextView = rootView.findViewById<TextView>(R.id.tv_distance)
    val tvFeature: TextView = rootView.findViewById<TextView>(R.id.tv_feature)
    val ivFavorite: ImageView = rootView.findViewById<ImageView>(R.id.iv_favorite)

}
