package com.th.android.comm.log

interface ILog {
    /**
     * Log.i("SomeTag", "${msgTag}: ${msg}")
     */
    fun i(msg: String, msgTag: String)
    fun i(msg: String) = i(msg, "")

    fun d(msg: String, msgTag: String)
    fun d(msg: String) = d(msg, "")

    fun w(msg: String, msgTag: String)
    fun w(msg: String) = w(msg, "")

    fun e(msg: String, msgTag: String)
    fun e(msg: String) = e(msg, "")

    fun err(err: Throwable?, prefix: String, msgTag: String)
    fun err(err: Throwable?, prefix: String) = err(err, prefix, "")
    fun err(err: Throwable?) = err(err, "")
}