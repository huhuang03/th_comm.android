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

open class ListCache(private val mCaches: List<ICache>): ICache {
    override fun save(id: String) {
        mCaches.forEach { it.save(id) }
    }

    override fun load(): String {
        var id = mCaches.firstOrNull { it.load().isNotEmpty() }?.load()?: ""
        if (id.isBlank()) {
            id = ""
        }
        return id
    }
}

class IntervalCache(context: Context, spCacheKey: String): ListCache(listOf(MemoryIdCache(), SpCache(spCacheKey, context))) {}

class CommonCache(context: Context, spCacheKey: String, fileCachePath: String): ListCache(mutableListOf(
    MemoryIdCache(), SpCache(spCacheKey, context)
).apply {
    if (fileCachePath.isBlank()) {
        add(SdCardCache(fileCachePath))
    }
}) {
    constructor(context: Context, spCacheKey: String): this(context, spCacheKey, "")
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


class SpCache(val key: String, context: Context): ICache {
    val mContext = context.applicationContext

    override fun save(id: String) {
        spSaveStr(mContext, key, id)
    }

    override fun load(): String {
        return spGetStr(mContext, key)
    }

}

class SdCardCache(private val fileName: String): ICache {

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

