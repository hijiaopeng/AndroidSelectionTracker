package com.jiaopeng.androidselectiontracker

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView

class MyLookup(private val recyclerView: RecyclerView) :
    ItemDetailsLookup<Person>() {

    override fun getItemDetails(e: MotionEvent): ItemDetails<Person>? {
        val view = recyclerView.findChildViewUnder(e.x, e.y)
        if (view != null) {
            return (recyclerView.getChildViewHolder(view) as ItemAdapter.ItemViewHolder).itemDetails
        }
        return null
    }

}