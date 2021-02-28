package com.lumi.surfeducationproject.ui.extension.activity_extension

import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.setAdjustPan(){
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
}

fun FragmentActivity.setAdjustResize(){
    window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
}