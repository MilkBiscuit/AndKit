package com.cheng.andkit.sample.ui.store

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.cheng.andkit.sample.R
import com.cheng.andkit.sample.domain.model.PlexureStore
import java.util.Locale

class PlexureStoreAdapter(
    val activity: FragmentActivity,
    private val storeListVM: StoreListViewModel,
): RecyclerView.Adapter<PlexureStoreViewHolder>() {

    var items: List<PlexureStore> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlexureStoreViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_plexure_store, parent, false)

        return PlexureStoreViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: PlexureStoreViewHolder, i: Int) {
        val item = items[i]

        viewHolder.tvName.text = item.name
        viewHolder.tvAddress.text = item.address
        viewHolder.tvDistance.text = formatDistance(item.distance)
        if (item.featureList.isNullOrEmpty()) {
            viewHolder.tvFeature.visibility = View.GONE
        } else {
            viewHolder.tvFeature.visibility = View.VISIBLE
            viewHolder.tvFeature.text = formatFeatureList(item.featureList)
        }
        val greyOut = PlexureConstants.GREY_DISTANT_STORES
                && item.distance > PlexureConstants.GREY_DISTANCE_IN_METER
        viewHolder.tvName.isEnabled = !greyOut
        viewHolder.tvAddress.isEnabled = !greyOut
        viewHolder.tvDistance.isEnabled = !greyOut
        viewHolder.tvFeature.isEnabled = !greyOut

        val favoriteStoreIds = storeListVM.favoriteStoreIds
        val inFavorite = favoriteStoreIds.any { it == item.id }
        val imageId =
                if (inFavorite) R.drawable.ic_favorite_red_24dp
                else R.drawable.ic_favorite_border_red_24dp
        viewHolder.ivFavorite.setImageResource(imageId)
        viewHolder.ivFavorite.setOnClickListener {
            storeListVM.toggleFavourite(item.id!!)
            notifyItemChanged(i)
        }
    }

    private fun formatFeatureList(featureList: List<String>): String {
        return featureList.joinToString(", ").lowercase(Locale.ENGLISH)
    }

    private fun formatDistance(distanceInMeter: Int): String {
        val distanceInKm = distanceInMeter / 1000

        return "$distanceInKm km"
    }

}
