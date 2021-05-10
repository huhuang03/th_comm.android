package com.th.android.comm.ex

import android.util.Log
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

fun File.ensureFolder() {
    if (this.parentFile?.exists() != true) {
        this.parentFile?.mkdirs()
    }
}

fun File.copyInputStreamToFile(inputStream: InputStream) {
    this.ensureFolder()
    this.outputStream().use { fileOut ->
        inputStream.copyTo(fileOut)
    }
}

fun File.writeTextEx(text: String) {
    this.ensureFolder()
    try {
        this.writeText(text)
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    }
}

fun File.readTextEx(): String {
    if (!this.exists()) {
        return ""
    }
    return readText()
}
