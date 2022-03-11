package com.th.android.comm.util

import android.content.Context

const val SP_DEFAULT_FILE = "config"

@JvmOverloads
fun spSaveBool(context: Context, key: String, value: Boolean, spFileName: String = SP_DEFAULT_FILE) {
    context.getSharedPreferences(spFileName, Context.MODE_PRIVATE)
        .edit().putBoolean(key, value).apply()
}

@JvmOverloads
fun spGetBool(context: Context, key: String, defValue: Boolean = false, spName: String = SP_DEFAULT_FILE): Boolean {
    return context.getSharedPreferences(spName, Context.MODE_PRIVATE)
        .getBoolean(key, defValue)
}

@JvmOverloads
fun spSaveStr(context: Context, key: String, value: String, spFileName: String = SP_DEFAULT_FILE) {
    context.getSharedPreferences(spFileName, Context.MODE_PRIVATE)
        .edit().putString(key, value).apply()
}

@JvmOverloads
fun spGetStr(context: Context, key: String, defValue: String = "", spName: String = SP_DEFAULT_FILE): String {
     return context.getSharedPreferences(spName, Context.MODE_PRIVATE)
        .getString(key, defValue)?: ""
}

@JvmOverloads
fun spSaveLong(context: Context, key: String, value: Long, spFileName: String = SP_DEFAULT_FILE) {
    context.getSharedPreferences(spFileName, Context.MODE_PRIVATE)
        .edit().putLong(key, value).apply()
}

@JvmOverloads
fun spGetLong(context: Context, key: String, defValue: Long = 0, spName: String = SP_DEFAULT_FILE): Long {
    return context.getSharedPreferences(spName, Context.MODE_PRIVATE)
        .getLong(key, defValue)
}
