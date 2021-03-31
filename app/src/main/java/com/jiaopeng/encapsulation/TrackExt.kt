package com.jiaopeng.encapsulation

import android.os.Parcelable
import android.util.Log
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.RecyclerView
import com.jiaopeng.androidselectiontracker.MyKeyProvider
import com.jiaopeng.androidselectiontracker.MyLookup
import com.jiaopeng.androidselectiontracker.Person
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * 描述：
 *
 * @author JiaoPeng by 3/25/21
 */
/**
 * RecyclerView简单实现多选功能
 */
inline fun <reified T : Parcelable> RecyclerView.initMultipleSelectTrack(
    mAdapter: BaseTrackerAdapter<T>,
    maxSize: Int = 5,
    noinline maxInfo: (() -> Unit)? = null//超出选择数量做相应处理
): SelectionTracker<T> {
    var mSelectionTracker: SelectionTracker<T>? = null
    setHasFixedSize(true)
    adapter = mAdapter
    mSelectionTracker = SelectionTracker
        .Builder(
            "items-selection${Calendar.getInstance().timeInMillis}",
            this,
            BaseTrackKeyProvider(mAdapter),
            BaseTrackLookUp<T>(this),
            StorageStrategy.createParcelableStorage(T::class.java)
        )
        .withSelectionPredicate(object : SelectionTracker.SelectionPredicate<T>() {
            override fun canSelectMultiple(): Boolean {
                return true
            }

            override fun canSetStateForKey(key: T, nextState: Boolean): Boolean {
                return if (nextState && mSelectionTracker?.selection?.size()!! >= maxSize) {
                    false
                } else {
                    if (nextState) {
                        maxInfo?.invoke()
                    }
                    true
                }
            }

            override fun canSetStateAtPosition(position: Int, nextState: Boolean): Boolean {
                return true
            }
        })
        .build()
    mAdapter.mSelectionTracker = mSelectionTracker
    return mSelectionTracker
}

/**
 * RecyclerView简单实现单选功能
 */
inline fun <reified T : Parcelable> RecyclerView.initSingleSelectTrack(
    mAdapter: BaseTrackerAdapter<T>
): SelectionTracker<T> {
    var mSelectionTracker: SelectionTracker<T>? = null
    setHasFixedSize(true)
    adapter = mAdapter
    mSelectionTracker = SelectionTracker
        .Builder(
            "items-selection${Calendar.getInstance().timeInMillis}",
            this,
            BaseTrackKeyProvider(mAdapter),
            BaseTrackLookUp<T>(this),
            StorageStrategy.createParcelableStorage(T::class.java)
        )
        .withSelectionPredicate(SelectionPredicates.createSelectSingleAnything())
        .build()
    mAdapter.mSelectionTracker = mSelectionTracker
    return mSelectionTracker
}

/**
 * RecyclerView简单实现仿单选按钮功能
 */
inline fun <reified T : Parcelable> RecyclerView.initRadioSelectTrack(
    mAdapter: BaseTrackerAdapter<T>
): SelectionTracker<T> {
    var mSelectionTracker: SelectionTracker<T>? = null
    setHasFixedSize(true)
    adapter = mAdapter
    mSelectionTracker = SelectionTracker
        .Builder(
            "items-selection${Calendar.getInstance().timeInMillis}",
            this,
            BaseTrackKeyProvider(mAdapter),
            BaseTrackLookUp<T>(this),
            StorageStrategy.createParcelableStorage(T::class.java)
        )
        .withSelectionPredicate(object : SelectionTracker.SelectionPredicate<T>() {
            override fun canSelectMultiple(): Boolean {
                return false
            }

            override fun canSetStateForKey(key: T, nextState: Boolean): Boolean {
                return nextState && mSelectionTracker?.selection?.firstOrNull() != key
            }

            override fun canSetStateAtPosition(position: Int, nextState: Boolean): Boolean {
                return true
            }
        })
        .build()
    mAdapter.mSelectionTracker = mSelectionTracker
    return mSelectionTracker
}