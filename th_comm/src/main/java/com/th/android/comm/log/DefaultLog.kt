package com.th.android.comm.log

import android.util.Log
import java.lang.Exception

/**
 * @param tag Log.i(tag, "xxx")
 */
open class ThDefaultLog(private val tag: String, private val enable: Boolean) : ILog {

    override fun i(msg: String, msgTag: String) {
        if (enable) {
            Log.i(tag, "${msgTag(msgTag)}$msg")
        }
    }

    override fun d(msg: String, msgTag: String) {
        if (enable) {
            Log.d(tag, "${msgTag(msgTag)}$msg")
        }
    }

    override fun w(msg: String, msgTag: String) {
        if (enable) {
            Log.w(tag, "${msgTag(msgTag)}$msg")
        }
    }

    override fun e(msg: String, msgTag: String) {
        if (enable) {
            Log.e(tag, "${msgTag(msgTag)}$msg")
        }
    }

    override fun err(err: Throwable?, prefix: String, msgTag: String) {
        if (enable) {
            val errTmp = err ?: Exception("Exception is null")

            e("${errPrefix(prefix)}${Log.getStackTraceString(errTmp)}", msgTag)
        }
    }

    private fun msgTag(msgTag: String?): String {
        if (msgTag != null && msgTag.isNotBlank())  {
            return "${msgTag}: "
        }
        return ""
    }

    private fun errPrefix(errPrefix: String?): String {
        return if (errPrefix == null || errPrefix.isBlank()) {
            ""
        } else {
           "$errPrefix "
        }
    }

}