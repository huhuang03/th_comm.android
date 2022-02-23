//package com.th.android.comm
//
//import android.content.Context
//import android.os.Environment
//import com.th.android.comm.ex.readTextEx
//import com.th.android.comm.ex.writeTextEx
//import com.th.android.comm.util.spGetStr
//import com.th.android.comm.util.spSaveStr
//import java.io.File
//import java.lang.Exception
//
///**
// * 我允许null吗？允许的话会更简单一点吧。我们暂时先允许试试
// */
//interface IObjCache<T> {
//    fun save(id: T?)
//    fun load(): T?
//}
//
//class CommonCache(context: Context, spCacheKey: String, fileCachePath: String, val defaultVal: () -> String): ICache {
//    private var mCaches = listOf(MemoryIdCache(), SpIdCache(spCacheKey, context), SdCardIdCache(fileCachePath))
//
//    override fun save(id: String) {
//        mCaches.forEach { it.save(id) }
//    }
//
//    override fun load(): String {
//        var id = mCaches.firstOrNull { it.load().isNotEmpty() }?.load()?: ""
//        if (id.isBlank()) {
//            id = defaultVal()
//        }
//        return id
//    }
//}
//
//
//class MemoryObjCache<T>: IObjCache<T> {
//    private var cached: T? = null
//
//    override fun save(id: T?) {
//        cached = id
//    }
//
//    override fun load(): T? {
//        return cached
//    }
//}
//
//
//class SpObjCache<T>(val key: String, context: Context): IObjCache<T> {
//    val mContext = context.applicationContext
//
//    override fun save(value: T?) {
//        if (value != null) {
//            try {
//                spSaveStr(mContext, key, Gson())
//            } catch (e: Exception) {
//                // ignore
//            }
//        }
//    }
//
//    override fun load(): T? {
//        return spGetStr(mContext, key)
//    }
//
//}
//
//class SdCardIdCache(private val fileName: String): ICache {
//
//    override fun save(id: String) {
//        File(getCacheFilePath()).writeTextEx(id)
//    }
//
//    override fun load(): String {
//        return File(getCacheFilePath()).readTextEx()
//    }
//
//
//    private fun getCacheFilePath(): String {
//        return Environment.getExternalStorageDirectory().absolutePath  + File.separator + fileName
//    }
//}
//
