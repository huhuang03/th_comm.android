package com.th.android.comm.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.th.android.comm.TLog
import com.th.android.comm.tlog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val log1 = TLog("log1")
    }
}