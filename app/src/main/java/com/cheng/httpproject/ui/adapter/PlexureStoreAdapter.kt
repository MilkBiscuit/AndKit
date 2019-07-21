package com.cheng.httpproject.ui.adapter

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheng.httpproject.R
import com.cheng.httpproject.constant.PlexureConstants
import com.cheng.httpproject.helper.PlexureFavoriteIdDao
import com.cheng.httpproject.model.PlexureStore
import com.cheng.httpproject.ui.viewmodel.PlexureStoreViewModel
import com.cheng.httpproject.util.StringUtil

class PlexureStoreAdapter(val activity: FragmentActivity, listData: List<PlexureStore>)
    : RecyclerView.Adapter<PlexureStoreViewHolder>() {

    val viewModel: PlexureStoreViewModel = ViewModelProviders.of(activity).get(PlexureStoreViewModel::class.java)

    var items: List<PlexureStore> = listData
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlexureStoreViewHolder {
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_plexure_store, parent, false)

        return PlexureStoreViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: PlexureStoreViewHolder, i: Int) {
        val item = items[i]

        viewHolder.tvName.text = item.name
        viewHolder.tvAddress.text = item.address
        viewHolder.tvDistance.text = StringUtil.formatDistance(item.distance)
        if (item.featureList.isNullOrEmpty()) {
            viewHolder.tvFeature.visibility = View.GONE
        } else {
            viewHolder.tvFeature.visibility = View.VISIBLE
            viewHolder.tvFeature.text = StringUtil.formatFeatureList(item.featureList!!.toList())
        }
        val greyOut = PlexureConstants.GREY_DISTANT_STORES
                && item.distance > PlexureConstants.GREY_DISTANCE_IN_METER
        viewHolder.tvName.isEnabled = !greyOut
        viewHolder.tvAddress.isEnabled = !greyOut
        viewHolder.tvDistance.isEnabled = !greyOut
        viewHolder.tvFeature.isEnabled = !greyOut

        val favoriteStoreIds = viewModel.getFavoriteStoreIds()
        val inFavorite = favoriteStoreIds.any { it == item.id }
        val imageId =
                if (inFavorite) R.drawable.ic_favorite_red_24dp
                else R.drawable.ic_favorite_border_red_24dp
        viewHolder.ivFavorite.setImageResource(imageId)
        viewHolder.ivFavorite.setOnClickListener {
            val favoriteIdDao = PlexureFavoriteIdDao.getInstance(activity)
            favoriteIdDao.toggleFavorite(item.id!!)

            notifyItemChanged(i)
        }
    }

}