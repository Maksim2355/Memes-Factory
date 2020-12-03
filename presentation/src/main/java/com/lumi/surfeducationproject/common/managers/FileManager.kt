package com.lumi.surfeducationproject.common.managers

import android.graphics.Bitmap

interface FileManager {

    fun saveImg(imgBtmp: Bitmap): String?

}