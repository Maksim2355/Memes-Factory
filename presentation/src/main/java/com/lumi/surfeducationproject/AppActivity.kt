package com.lumi.surfeducationproject

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.lumi.surfeducationproject.common.REQUEST_CODE_PERMISSION_CAMERA
import com.lumi.surfeducationproject.common.REQUEST_CODE_PERMISSION_GALLERY
import com.lumi.surfeducationproject.common.managers.*
import com.lumi.surfeducationproject.navigation.NavigationAuth
import com.lumi.surfeducationproject.navigation.NavigationContent
import com.lumi.surfeducationproject.navigation.NavigationStartApp
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class AppActivity : AppCompatActivity(), NavigationStartApp, NavigationContent, StyleManager,
    NavigationAuth, SnackBarManager, PermissionManager, FileManager, InputModeManager {

    private val navController: NavController by lazy {
        findNavController(R.id.nav_host_fragment_container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.instance.startActivityComponent(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        App.instance.clearActivityComponent()
    }

    override fun startApp(isAuthUser: Boolean) {
        if (isAuthUser) {
            navController.navigate(R.id.action_splashFragment_to_tabFragment)
        } else {
            navController.navigate(R.id.action_splashFragment_to_authFragment)
        }
    }

    override fun startContentScreen() {
        navController.navigate(R.id.action_authFragment_to_tabFragment)
    }

    override fun setColorStatusBar(color: Int) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(color)
    }

    override fun startAuthScreen() {
        navController.navigate(R.id.action_tabFragment_to_authFragment)
    }

    override fun showErrorMessage(message: String) {
        val snackbar = Snackbar.make(
            root_layout, message,
            Snackbar.LENGTH_LONG
        )
        snackbar.view.setBackgroundColor(resources.getColor(R.color.colorError))
        val textView =
            snackbar.view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.WHITE)
        textView.textSize = 16f
        snackbar.show()
    }

    override fun requestPermissionGallery(): Boolean {
        val permissionReadExternalStorage =
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
        return if (permissionReadExternalStorage == PackageManager.PERMISSION_GRANTED) {
            true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                REQUEST_CODE_PERMISSION_GALLERY
            )
            false
        }
    }

    override fun saveImg(imgBtmp: Bitmap): String? {
        val saveDir = File(cacheDir, "meme")
        return try {
            if (!saveDir.exists()) saveDir.mkdir()
            val file = File(saveDir, getImgFileName())
            val outStr = FileOutputStream(file)
            imgBtmp.compress(Bitmap.CompressFormat.JPEG, 100, outStr) // bmp is your Bitmap instance
            outStr.flush()
            outStr.close()
            FileProvider.getUriForFile(
                this,
                "",
                file
            ).toString()
        } catch (ex: IOException) {
            null
        }
    }

    private fun getImgFileName(): String = "Meme_${System.currentTimeMillis()}"

    override fun setAdjustPan() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }

    override fun setAdjustResize() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

}