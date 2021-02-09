package com.lumi.surfeducationproject.ui.extension.activity_extension

import android.view.WindowManager
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.setColorStatusBar(color: Int) {
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = resources.getColor(color, null)
}