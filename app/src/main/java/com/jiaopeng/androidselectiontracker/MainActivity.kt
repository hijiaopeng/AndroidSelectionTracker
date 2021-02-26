package com.jiaopeng.androidselectiontracker

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mListData = arrayListOf(
        Person("1", "aaaa"),
        Person("2", "bbbb"),
        Person("3", "cccc"),
        Person("4", "dddd"),
        Person("5", "eeee"),
        Person("6", "ffff"),
        Person("7", "gggg"),
        Person("8", "hhhh")
    )

    private val mAdapter: ItemAdapter by lazy {
        ItemAdapter(mListData)
    }

    private var mSelectionTracker: SelectionTracker<Person>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMain?.setHasFixedSize(true)
        rvMain?.adapter = mAdapter

        mSelectionTracker = SelectionTracker
            .Builder(
                "items-selection",
                rvMain,
                MyKeyProvider(mAdapter),
                MyLookup(rvMain),
                StorageStrategy.createParcelableStorage(Person::class.java)
            )
//            .withSelectionPredicate(SelectionPredicates.createSelectAnything())
            .withSelectionPredicate(object : SelectionTracker.SelectionPredicate<Person>() {
                override fun canSelectMultiple(): Boolean {
                    return true
                }

                override fun canSetStateForKey(key: Person, nextState: Boolean): Boolean {
                    return if (nextState && mSelectionTracker?.selection?.size()!! >= 5) {
                        false
                    } else {
                        if (nextState) {
                            Log.e("TAG", "info")
                        }
                        true
                    }
                    //设置最大选择数量
                    //return !(nextState && mPsTracker?.selection?.size()!! >= 5)
                }

                override fun canSetStateAtPosition(position: Int, nextState: Boolean): Boolean {
                    return true
                }
            })
            .build()

        mAdapter.mSelectionTracker = mSelectionTracker

        mSelectionTracker?.addObserver(object : SelectionTracker.SelectionObserver<Person>() {
            override fun onSelectionChanged() {
                super.onSelectionChanged()
                if (mSelectionTracker?.hasSelection()!!) {
                    mSelectionTracker?.selection?.forEach {
                        Log.e("TAG", "Selection : $it")
                    }
                } else {
                    Log.e("TAG", "Selection Count: 0")
                }
            }
        })

        if (savedInstanceState != null)
            mSelectionTracker?.onRestoreInstanceState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mSelectionTracker?.onSaveInstanceState(outState)
    }
}