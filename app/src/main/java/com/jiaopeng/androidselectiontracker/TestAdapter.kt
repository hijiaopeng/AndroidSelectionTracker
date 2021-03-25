package com.jiaopeng.androidselectiontracker

import android.graphics.Color
import com.jiaopeng.encapsulation.BaseTrackerAdapter

/**
 * 描述：
 *
 * @author JiaoPeng by 3/25/21
 */
class TestAdapter(mList: ArrayList<Person>) : BaseTrackerAdapter<Person>(
    R.layout.list_item,
    mList
) {

    override fun bind(holder: BaseTrackerHolder, item: Person, isSelected: Boolean) {
        holder.setText(R.id.tvItemId, item.id)
        holder.setText(R.id.tvItemName, item.name)
        if (isSelected) {
            holder.setTextColor(R.id.tvItemName, Color.parseColor("#000000"))
        } else {
            holder.setTextColor(R.id.tvItemName, Color.parseColor("#de1c31"))
        }
    }

}