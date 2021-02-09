package com.lumi.surfeducationproject.ui.extension.activity_extension

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import com.lumi.surfeducationproject.R

fun FragmentActivity.showErrorMessage(message: String, v: View){
    val snackBar = Snackbar.make(
        v, message,
        Snackbar.LENGTH_LONG
    )
    snackBar.view.setBackgroundColor(getColor(R.color.colorError))
    val textView =
        snackBar.view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
    textView.setTextColor(Color.WHITE)
    textView.textSize = 16f
    snackBar.show()
}