package com.cheng.httpproject.ui.adapter

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.cheng.httpproject.R
import com.cheng.httpproject.helper.PlexureFavoriteIdDao
import com.cheng.httpproject.model.PlexureStore
import com.cheng.httpproject.ui.adapter.base.TwoLinesAdapter
import com.cheng.httpproject.ui.adapter.base.TwoLinesViewHolder
import com.cheng.httpproject.ui.viewmodel.PlexureStoreViewModel

class PlexureStoreAdapter(val activity: FragmentActivity, items: List<PlexureStore>):
        TwoLinesAdapter<PlexureStore>(activity, items) {

    val viewModel: PlexureStoreViewModel
    init {
        viewModel = ViewModelProviders.of(activity).get(PlexureStoreViewModel::class.java)
    }

    override fun bindItemViewHolder(viewHolder: TwoLinesViewHolder, i: Int, item: PlexureStore) {
        viewHolder.tvLine1.text = item.name
        viewHolder.tvLine2.text = item.address

        val favoriteStoreIds = viewModel.getFavoriteStoreIds()
        val inFavorite = favoriteStoreIds.any { it == item.id }
        val imageId =
                if (inFavorite) R.drawable.ic_favorite_red_24dp
                else R.drawable.ic_favorite_border_red_24dp
        viewHolder.ivChevron.setImageResource(imageId)
        viewHolder.ivChevron.setOnClickListener {
            val favoriteIdDao = PlexureFavoriteIdDao.getInstance(activity)
            favoriteIdDao.toggleFavorite(item.id!!)

            notifyItemChanged(i)
        }
    }

}