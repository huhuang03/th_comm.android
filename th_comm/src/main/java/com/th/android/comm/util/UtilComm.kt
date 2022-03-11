package com.th.android.comm.util

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method


fun getProcessName(fallback: String = ""): String {
    return if (Build.VERSION.SDK_INT >= 28) Application.getProcessName()
    else try {
        @SuppressLint("PrivateApi") val activityThread = Class.forName("android.app.ActivityThread")

        // Before API 18, the method was incorrectly named "currentPackageName", but it still returned the process name
        // See https://github.com/aosp-mirror/platform_frameworks_base/commit/b57a50bd16ce25db441da5c1b63d48721bb90687
        val methodName = "currentProcessName"
        @SuppressLint("DiscouragedPrivateApi") val getProcessName: Method =
            activityThread.getDeclaredMethod(methodName)
        (getProcessName.invoke(null) as String?)?: fallback
    } catch (e: Throwable) {
        // ignore?
        fallback
    }
}
