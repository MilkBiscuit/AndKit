package com.cheng.httpproject.ui.adapter

import android.content.Context
import com.cheng.httpproject.R
import com.cheng.httpproject.model.PlexureStore
import com.cheng.httpproject.ui.adapter.base.TwoLinesAdapter
import com.cheng.httpproject.ui.adapter.base.TwoLinesViewHolder
import io.realm.Realm

class PlexureStoreAdapter(context: Context, items: List<PlexureStore>):
        TwoLinesAdapter<PlexureStore>(context, items) {

    override fun bindItemViewHolder(viewHolder: TwoLinesViewHolder, i: Int, item: PlexureStore) {
        viewHolder.tvLine1.text = item.name
        viewHolder.tvLine2.text = item.address

//        val imageId = if (item.favourite == 0) R.drawable.ic_favorite_border_red_24dp
//        else R.drawable.ic_favorite_red_24dp
//        viewHolder.ivChevron.setImageResource(imageId)
        viewHolder.ivChevron.setOnClickListener {
            val id = item.id
            val realm = Realm.getDefaultInstance()
            realm.executeTransactionAsync {
                val store = it.where(PlexureStore::class.java).equalTo("id", id).findFirst()
                store?.deleteFromRealm()
            }
            realm.close()
        }
    }

}