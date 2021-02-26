package com.jiaopeng.androidselectiontracker

import androidx.recyclerview.selection.ItemDetailsLookup

class MyItemDetails(private val position: Int, private val item: Person) :
    ItemDetailsLookup.ItemDetails<Person>() {

    override fun getPosition(): Int {
        return position
    }

    override fun getSelectionKey(): Person {
        return item
    }

}