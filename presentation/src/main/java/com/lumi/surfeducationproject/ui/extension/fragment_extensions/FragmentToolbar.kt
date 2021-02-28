package com.lumi.surfeducationproject.ui.extension.fragment_extensions

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide


fun Fragment.setToolbar(toolbar: Toolbar) {
    (activity as AppCompatActivity).setSupportActionBar(toolbar)
}

fun Fragment.loadAvatars(
    iv: ImageView,
    url: String = "https://img.pngio.com/avatar-1-length-of-human-face-hd-png-download-6648260-free-human-face-png-840_640.png"
) {
    Glide.with(this)
        .load(url)
        .optionalCircleCrop()
        .into(iv)
}