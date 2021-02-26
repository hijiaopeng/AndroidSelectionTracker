package com.jiaopeng.androidselectiontracker

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Person(
    val id: String? = null,
    val name: String? = null
) : Parcelable