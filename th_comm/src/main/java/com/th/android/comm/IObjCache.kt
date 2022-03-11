package com.th.android.comm

import android.content.Context
import android.text.TextUtils
import com.google.gson.Gson


interface IObjCache<T> {
    /**
     * save with null means clean or ignore?
     */
    fun save(obj: T?)

    // always not return null
    fun load(clazz: Class<T>): T?
}

open class ObjFromICache<T>(private val iCache: ICache): IObjCache<T> {
    // no gson?
    override fun save(obj: T?) {
        val str = if (obj == null) {
            ""
        } else {
            Gson().toJson(obj)
        }
        iCache.save(str)
    }

    // aha?
    override fun load(clazz: Class<T>): T? {
        val json = iCache.load()
        if (TextUtils.isEmpty(json)) {
            return null
        }
        return Gson().fromJson(json, clazz)
    }
}

class IntervalObjCache<T>(context: Context, spCacheKey: String): ObjFromICache<T>(IntervalCache(context, spCacheKey))

class MemoryObjCache<T>: ObjFromICache<T>(MemoryIdCache())

class SpObjCache<T>(val key: String, context: Context): ObjFromICache<T>(SpCache(key, context))

class SdCardObjCache<T>(fileName: String): ObjFromICache<T>(SdCardCache(fileName))