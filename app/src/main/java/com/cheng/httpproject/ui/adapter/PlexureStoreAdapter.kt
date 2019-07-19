package com.cheng.httpproject.ui.adapter

import android.content.Context
import android.view.View
import com.cheng.httpproject.model.PlexureStore
import com.cheng.httpproject.ui.adapter.base.TwoLinesAdapter
import com.cheng.httpproject.ui.adapter.base.TwoLinesViewHolder

class PlexureStoreAdapter(context: Context, items: List<PlexureStore>):
        TwoLinesAdapter<PlexureStore>(context, items) {

    override fun bindItemViewHolder(viewHolder: TwoLinesViewHolder, i: Int, item: PlexureStore) {
        viewHolder.tvLine1.text = item.name
        viewHolder.tvLine2.text = item.address
        viewHolder.ivChevron.visibility = View.GONE
    }

}