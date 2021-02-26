package com.jiaopeng.androidselectiontracker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(
    private val mList: List<Person>
) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    var mSelectionTracker: SelectionTracker<Person>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = mList[position]
        mSelectionTracker?.isSelected(item)?.let { holder.bind(item, it) }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun getItemId(position: Int): Long {
        return mList[position].hashCode().toLong()
    }

    fun getItem(position: Int): Person = mList[position]

    fun getPosition(person: Person): Int = mList.indexOf(person)

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvItemId: TextView = itemView.findViewById(R.id.tvItemId)
        private val tvItemName: TextView = itemView.findViewById(R.id.tvItemName)

        fun bind(item: Person?, isSelected: Boolean) {
            itemView.setOnClickListener {
                if (mSelectionTracker?.selection?.size() == 0) {
                    mSelectionTracker?.select(item!!)
                }
            }
            tvItemId.text = item?.id
            tvItemName.text = item?.name
            if (isSelected)
                tvItemName.setTextColor(Color.parseColor("#000000"))
            else
                tvItemName.setTextColor(Color.parseColor("#de1c31"))
        }

        val itemDetails: ItemDetailsLookup.ItemDetails<Person>
            get() = MyItemDetails(absoluteAdapterPosition, mList[absoluteAdapterPosition])

    }

}
