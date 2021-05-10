package com.th.android.comm

import android.content.Context
import android.os.Environment
import com.th.android.comm.ex.readTextEx
import com.th.android.comm.ex.writeTextEx
import com.th.android.comm.util.spGetStr
import com.th.android.comm.util.spSaveStr
import java.io.File


interface ICache {
    fun save(id: String)
    fun load(): String
}

class CommonCache(context: Context, spCacheKey: String, fileCachePath: String, val defaultVal: () -> String): ICache {
    private var mCaches = listOf(MemoryIdCache(), SpIdCache(spCacheKey, context), SdCardIdCache(fileCachePath))

    override fun save(id: String) {
        mCaches.forEach { it.save(id) }
    }

    override fun load(): String {
        var id = mCaches.firstOrNull { it.load().isNotEmpty() }?.load()?: ""
        if (id.isBlank()) {
            id = defaultVal()
        }
        return id
    }
}


class MemoryIdCache: ICache {
    private var cached  = ""

    override fun save(id: String) {
        cached = id
    }

    override fun load(): String {
        return cached
    }
}


class SpIdCache(val key: String, context: Context): ICache {
    val mContext = context.applicationContext

    override fun save(id: String) {
        spSaveStr(mContext, key, id)
    }

    override fun load(): String {
        return spGetStr(mContext, key)
    }

}

class SdCardIdCache(private val fileName: String): ICache {

    override fun save(id: String) {
        File(getCacheFilePath()).writeTextEx(id)
    }

    override fun load(): String {
        return File(getCacheFilePath()).readTextEx()
    }


    private fun getCacheFilePath(): String {
        return Environment.getExternalStorageDirectory().absolutePath  + File.separator + fileName
    }
}

