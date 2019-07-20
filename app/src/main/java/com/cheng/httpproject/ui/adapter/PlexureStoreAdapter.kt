package com.cheng.httpproject.ui.adapter

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.cheng.httpproject.R
import com.cheng.httpproject.constant.PlexureConstants
import com.cheng.httpproject.model.PlexureFavoriteStoreId
import com.cheng.httpproject.model.PlexureStore
import com.cheng.httpproject.ui.adapter.base.TwoLinesAdapter
import com.cheng.httpproject.ui.adapter.base.TwoLinesViewHolder
import com.cheng.httpproject.ui.viewmodel.PlexureStoreViewModel
import io.realm.Realm

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
            updateFavoriteInDB(item.id!!)

            notifyItemChanged(i)
        }
    }

    private fun updateFavoriteInDB(id: String) {
        val realm = Realm.getDefaultInstance()
        var plexureFavoriteStoreId = realm.where(PlexureFavoriteStoreId::class.java)
                .equalTo(PlexureConstants.FIELD_ID, id)
                .findFirst()
        realm.beginTransaction()
        if (plexureFavoriteStoreId != null) {
            plexureFavoriteStoreId.deleteFromRealm()
        } else {
            plexureFavoriteStoreId = PlexureFavoriteStoreId(id)
            realm.copyToRealm(plexureFavoriteStoreId)
        }
        realm.commitTransaction()
        realm.close()
    }

}