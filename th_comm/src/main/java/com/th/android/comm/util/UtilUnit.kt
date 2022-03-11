package com.th.android.comm.util

import android.content.res.Resources

class UtilUnit {
    fun dp2px(dp: Int): Int {
       return ((dp * Resources.getSystem().displayMetrics.density).toInt())
    }

    fun px2dp(dp: Int): Int {
        return ((dp / Resources.getSystem().displayMetrics.density).toInt())
    }
}