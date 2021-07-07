package com.th.android.comm

import android.util.Log

class TLog(var tag: String = "th_log", var enable: Boolean = true) {

    fun i(msg: String) {
        if (enable) {
            Log.i(tag, msg)
        }
    }

    fun d(msg: String) {
        if (enable) {
            Log.d(tag, msg)
        }
    }

    fun w(msg: String) {
        if (enable) {
            Log.w(tag, msg)
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

val tlog = TLog()