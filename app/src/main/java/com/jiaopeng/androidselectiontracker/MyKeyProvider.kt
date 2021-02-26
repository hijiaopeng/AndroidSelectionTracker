package com.jiaopeng.androidselectiontracker

import androidx.recyclerview.selection.ItemKeyProvider

class MyKeyProvider(private val adapter: ItemAdapter) :
    ItemKeyProvider<Person>(SCOPE_CACHED) {

    override fun getKey(position: Int): Person = adapter.getItem(position)

    override fun getPosition(key: Person): Int = adapter.getPosition(key)

}