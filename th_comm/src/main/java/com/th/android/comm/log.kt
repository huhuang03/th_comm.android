package com.th.android.comm

import android.util.Log

class TLog {
    var enable: Boolean = true
    var tag: String = "th_log"

    fun i(msg: String) {
        if (enable) {
            Log.i(tag, msg)
        }
    }

    fun e(msg: String) {
        if (enable) {
            Log.e(tag, msg)
        }
    }

    fun err(err: Throwable) {
        if (enable) {
            Log.e(tag, Log.getStackTraceString(err))
        }
    }
}

val log = TLog()