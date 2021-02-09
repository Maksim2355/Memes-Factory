package com.lumi.surfeducationproject.utils

import android.content.Context
import android.graphics.Bitmap
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

fun saveImg(context: Context, imgBtmp: Bitmap): String? {
    val saveDir = File(context.cacheDir, "meme")
    return try {
        if (!saveDir.exists()) saveDir.mkdir()
        val file = File(saveDir, "Meme_${System.currentTimeMillis()}")
        val outStr = FileOutputStream(file)
        imgBtmp.compress(Bitmap.CompressFormat.JPEG, 100, outStr) // bmp is your Bitmap instance
        outStr.flush()
        outStr.close()
        FileProvider.getUriForFile(
            context,
            "",
            file
        ).toString()
    } catch (ex: IOException) {
        null
    }
}