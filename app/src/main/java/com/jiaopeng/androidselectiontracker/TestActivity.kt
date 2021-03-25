package com.jiaopeng.androidselectiontracker

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jiaopeng.encapsulation.initMultipleSelectTrack
import com.jiaopeng.encapsulation.initSingleSelectTrack
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {

    private val testAdapter: TestAdapter by lazy {
        TestAdapter(
            arrayListOf(
                Person("1", "aaaa"),
                Person("2", "bbbb"),
                Person("3", "cccc"),
                Person("4", "dddd"),
                Person("5", "eeee"),
                Person("6", "ffff"),
                Person("7", "gggg"),
                Person("8", "hhhh")
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        val track = rv_test?.initSingleSelectTrack(
            testAdapter
        )

        btn_show?.setOnClickListener {
            track?.selection?.forEach {
                Log.e("JP", "onCreate: $it")
            }
        }

    }
}